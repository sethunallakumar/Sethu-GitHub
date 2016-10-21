package com.sella.chatbots.bo;

public class RequestBO {
	
	String sessionId;
	String loginSessionId;
	String query;
	String parameter;
	
	
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
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestBO [sessionId=").append(sessionId)
				.append(", loginSessionId=").append(loginSessionId)
				.append(", query=").append(query).append(", parameter=")
				.append(parameter).append("]");
		return builder.toString();
	}

}
