package com.sella.chatbots.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseParameters {
	
	@JsonProperty("duration")
	String duration;
	@JsonProperty("fromdate")
	String fromdate;
	@JsonProperty("todate")
	String todate;
	@JsonProperty("noofduration")
	String noofduration;
	@JsonProperty("products")
	List<String> products;

	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getNoofduration() {
		return noofduration;
	}
	public void setNoofduration(String noofduration) {
		this.noofduration = noofduration;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
	
}
