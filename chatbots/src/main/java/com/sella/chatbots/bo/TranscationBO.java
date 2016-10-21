package com.sella.chatbots.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranscationBO {

	@JsonProperty("custID")
	int custID;
	@JsonProperty("custName")
	String custName;
	@JsonProperty("MerchName")
	String MerchName;
	@JsonProperty("TranscationType")
	String TranscationType;
	@JsonProperty("transDate")
	Date transDate;
	@JsonProperty("transAmt")
	String transAmt;
	
	public TranscationBO(){}
	
	public TranscationBO(int custID, String custName, String merchName,
			String transcationType, Date transDate, String transAmt) {
		super();
		this.custID = custID;
		this.custName = custName;
		MerchName = merchName;
		TranscationType = transcationType;
		this.transDate = transDate;
		this.transAmt = transAmt;
	}
	
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMerchName() {
		return MerchName;
	}
	public void setMerchName(String merchName) {
		MerchName = merchName;
	}
	public String getTranscationType() {
		return TranscationType;
	}
	public void setTranscationType(String transcationType) {
		TranscationType = transcationType;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	
	

}
