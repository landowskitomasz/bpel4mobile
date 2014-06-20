package com.bpel4mobile.example.hotel.middleware.ws;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.bpel4mobile.escalation.service.NotificationService;
import com.bpel4mobile.internal.bean.UserData;

@Service
public class HotelNotificationService extends NotificationService {

	private static Logger logger = Logger.getLogger(HotelNotificationService.class.getName());
	
	@Override
	protected void sendNotification(Map<String, Object> notification, List<UserData> users) {
		logger.info("send notification invoked." + notification);
	}

}
