package com.sella.chatbots.bo;

public class ResponseBO {
	
	RequestBO request;
	String sessionId;
	String loginSessionId;
	Response response;
	
	public ResponseBO(){}
	
	public ResponseBO(RequestBO request, String sessionId,
			String loginSessionId, Response response) {
		super();
		this.request = request;
		this.sessionId = sessionId;
		this.loginSessionId = loginSessionId;
		this.response = response;
	}
	public RequestBO getRequest() {
		return request;
	}
	public void setRequest(RequestBO request) {
		this.request = request;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLoginSessionId() {
		return loginSessionId;
	}
	public void setLoginSessionId(String loginSessionId) {
		this.loginSessionId = loginSessionId;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	
	
	

}
