package com.sella.chatbots.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseResult {
	
	@JsonProperty("source")
	String source;
	@JsonProperty("resolvedQuery")
	String resolvedQuery;
	@JsonProperty("speech")
	String speech;
	@JsonProperty("action")
	String action;
	@JsonProperty("score")
	String score;
	@JsonProperty("parameters")
	ApiResponseParameters parameters;
	@JsonProperty("metadata")
	ApiResponseMetadata metadata;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getResolvedQuery() {
		return resolvedQuery;
	}
	public void setResolvedQuery(String resolvedQuery) {
		this.resolvedQuery = resolvedQuery;
	}
	public String getSpeech() {
		return speech;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public ApiResponseParameters getParameters() {
		return parameters;
	}
	public void setParameters(ApiResponseParameters parameters) {
		this.parameters = parameters;
	}
	public ApiResponseMetadata getMetadata() {
		return metadata;
	}
	public void setMetadata(ApiResponseMetadata metadata) {
		this.metadata = metadata;
	}
	
	
	

}
