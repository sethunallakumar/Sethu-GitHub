package com.sella.chatbots.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseMetadata {
	
	@JsonProperty("intentId")
	String intentId;
	@JsonProperty("webhookUsed")
	String webhookUsed;
	@JsonProperty("intentName")
	String intentName;
	@JsonProperty("inputContexts")
	String inputContexts[];
	@JsonProperty("outputContexts")
	String outputContexts[];
	@JsonProperty("contexts")
	String contexts[];
	
	public String getIntentId() {
		return intentId;
	}
	public void setIntentId(String intentId) {
		this.intentId = intentId;
	}
	public String getWebhookUsed() {
		return webhookUsed;
	}
	public void setWebhookUsed(String webhookUsed) {
		this.webhookUsed = webhookUsed;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String[] getInputContexts() {
		return inputContexts;
	}
	public void setInputContexts(String[] inputContexts) {
		this.inputContexts = inputContexts;
	}
	public String[] getOutputContexts() {
		return outputContexts;
	}
	public void setOutputContexts(String[] outputContexts) {
		this.outputContexts = outputContexts;
	}
	public String[] getContexts() {
		return contexts;
	}
	public void setContexts(String[] contexts) {
		this.contexts = contexts;
	}
	
	
	

}
