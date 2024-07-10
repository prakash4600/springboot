package com.fujitsu.fnc.vta.settingsmanager.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fujitsu.fnc.vta.settingsmanager.service.SettingsService;



@Component	
public class ComponentScheduler {

	@Value("${scheduler.interval}")
   	private long interval;
	
	private SettingsService service;
	
	 @Scheduled(fixedRateString = "${scheduler.interval}")
	    public void performTaskWithFixedRate() {
		 service.runSchedulerjob();
	    }

}