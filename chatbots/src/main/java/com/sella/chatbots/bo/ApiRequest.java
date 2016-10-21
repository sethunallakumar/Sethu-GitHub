package com.sella.chatbots.bo;

import java.util.ArrayList;
import java.util.List;

public class ApiRequest {
	List<String> query = new ArrayList<String>();
	String lang = "en";
	String sessionId;
	Contexts contexts;
	
	public Contexts getContexts() {
		return contexts;
	}

	public void setContexts(Contexts contexts) {
		this.contexts = contexts;
	}

	public class Contexts{
		String name = "SellaItEngTest";
		int lifespan = 4;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getLifespan() {
			return lifespan;
		}
		public void setLifespan(int lifespan) {
			this.lifespan = lifespan;
		}
		
		
	}

	public List<String> getQuery() {
		return query;
	}

	public void setQuery(List<String> query) {
		this.query = query;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	 
	

}
