package com.sella.chatbots.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	
	@JsonProperty("transcationBOs")
	List<TranscationBO> transcationBOs;
	
		public Transaction() {
		
	}

	public List<TranscationBO> getTranscationBOs() {
		return transcationBOs;
	}

	public void setTranscationBOs(List<TranscationBO> transcationBOs) {
		this.transcationBOs = transcationBOs;
	}
	
	

}
