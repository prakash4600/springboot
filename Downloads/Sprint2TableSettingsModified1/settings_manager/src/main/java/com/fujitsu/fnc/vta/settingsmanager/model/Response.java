package com.fujitsu.fnc.vta.settingsmanager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {	
	private boolean status;
	private String message;
	private Object data;
}
