package com.sella.chatbots.test;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sella.chatbots.bo.Transaction;
import com.sella.chatbots.bo.TranscationBO;

public class Test {

	public static void main(String srgs[]){
		
		String carJson =
		        "{ \"transcationBOs\" : [{ \"custName\" : \"Mercedes\", \"custID\" : 5 },{ \"custName\" : \"Mercedes\", \"custID\" : 6 }]}";
	
	ObjectMapper mapper = new ObjectMapper();
	Transaction obj = null;
	try {
		obj = mapper.readValue(carJson, Transaction.class);
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	List<TranscationBO> customers = obj.getTranscationBOs();
	for(Object tran:customers){
		TranscationBO bo = (TranscationBO) tran;
		System.out.println("tran ::"+bo.getCustName());
		System.out.println("tran ::"+bo.getMerchName());
	}
	
	
	}
	
}
