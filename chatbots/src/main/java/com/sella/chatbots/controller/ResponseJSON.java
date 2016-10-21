package com.sella.chatbots.controller;

public class ResponseJSON {
	
	String msg;
	String requestmsg;
	boolean valid;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRequestmsg() {
		return requestmsg;
	}
	public void setRequestmsg(String requestmsg) {
		this.requestmsg = requestmsg;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
