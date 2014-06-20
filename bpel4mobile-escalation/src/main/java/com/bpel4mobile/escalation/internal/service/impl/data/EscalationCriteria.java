package com.bpel4mobile.escalation.internal.service.impl.data;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.bpel4mobile.internal.model.Task;


public class EscalationCriteria {
	
	private static Logger logger = Logger.getLogger(EscalationCriteria.class.getName());
	
	private static final String MINUTE = "m";

	private static final String HOUR = "H";

	private static final String DAY = "D";
	
	private Long forCondition;
	
	private String taskName;
	
	private DeadlineType deadlineType;
	
	private String condition;

	public EscalationCriteria (String taskName, DeadlineType deadlineType, String forCondition, String condition){
		this.taskName = taskName;
		this.deadlineType = deadlineType;
		this.forCondition = parse(forCondition);
		this.condition = condition;
	}

	private Long parse(String pattern) {
		String number = pattern.substring(0, pattern.length() - 1);
		if(pattern.endsWith(MINUTE)){
			return Long.parseLong(number) * 1000;
		} else if (pattern.endsWith(HOUR)){
			return Long.parseLong(number) * 60 * 1000;
		} else if (pattern.endsWith(DAY)){
			return Long.parseLong(number) * 24 * 60 * 1000;
		} else {
			logger.log(Level.WARNING, "Unknow time unit.");
			return Long.MAX_VALUE;
		}
	}

	public Query query() {
		StringBuilder builder = new StringBuilder("{ $and : [");
		builder.append("{ 'name' : '").append(taskName).append("'},");
		builder.append("{ 'state' : '");
		builder.append( DeadlineType.start.equals(deadlineType) ? Task.State.ready.name(): Task.State.climed.name());
		builder.append("'},");
		builder.append("{ $where: \"(");
		builder.append(new Date().getTime());
		builder.append(" - this.");
		if(DeadlineType.start.equals(deadlineType)){
			builder.append("createDate");
		} else if (DeadlineType.complete.equals(deadlineType)){
			builder.append("climeDate");
		}
		builder.append(" ) > ");
		builder.append(forCondition);
		builder.append("\"}");
		if(!StringUtils.isEmpty(condition)){
			builder.append(",");
			builder.append(condition);
		}
		builder.append("] }");
		logger.info(builder.toString());
		return new BasicQuery(builder.toString());
	}
}