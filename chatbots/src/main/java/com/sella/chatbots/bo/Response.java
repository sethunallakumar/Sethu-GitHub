package com.sella.chatbots.bo;

import java.util.List;

import com.sella.chatbots.enums.ACTION;

public class Response {
	
	ACTION action;
	String message;
	List<String> suggestions;
	Boolean isList;
	List<String> list;
	List<String> link;
	
	public Response(){}
	
	public Response(ACTION action, String message, List<String> suggestions,
			boolean isList, List<String> list,List<String> link) {
		super();
		this.action = action;
		this.message = message;
		this.suggestions = suggestions;
		this.isList = isList;
		this.list = list;
		this.link = link;
	}
	
	public ACTION getAction() {
		return action;
	}
	public void setAction(ACTION action) {
		this.action = action;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}
	public boolean igetIsList() {
		return isList;
	}
	public void setIsList(boolean isList) {
		this.isList = isList;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getLink() {
		return link;
	}

	public void setLink(List<String> link) {
		this.link = link;
	}
	
	
	

}
