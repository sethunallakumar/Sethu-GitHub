package com.sella.chatbots.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseStatus {
	
	@JsonProperty("code")
	String code;
	@JsonProperty("errorType")
	String errorType;

}
