package com.sella.chatbots.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
	
	@JsonProperty("id")
	String id;
	@JsonProperty("timestamp")
	String timestamp;
	@JsonProperty("sessionId")
	String sessionId;
	@JsonProperty("result")
	ApiResponseResult result;
	@JsonProperty("status")
	ApiResponseStatus status;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public ApiResponseResult getResult() {
		return result;
	}
	public void setParameters(ApiResponseResult result) {
		this.result = result;
	}
	
	

}
