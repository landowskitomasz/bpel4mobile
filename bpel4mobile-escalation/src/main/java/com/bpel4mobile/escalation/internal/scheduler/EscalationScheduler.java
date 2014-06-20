package com.bpel4mobile.escalation.internal.scheduler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.internal.service.EscalationService;

@Service
public class EscalationScheduler {

	private static Logger logger = Logger.getLogger(EscalationScheduler.class.getName());
	
	@Autowired
	private EscalationService escalationService;
	
	@Scheduled(fixedDelay=30000)
	public void checkDeadlines(){
		logger.info("Escalation scheduler invoked, will check for incomming deadlines.");
		try{
			escalationService.checkDeadlines();
		}catch(Exception e){
			logger.log(Level.WARNING, "Unable to perform escalation from scheduler", e);
		}
	}
}
