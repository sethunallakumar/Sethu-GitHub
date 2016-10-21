package com.sella.chatbots.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sella.chatbots.bo.ApiResponse;
import com.sella.chatbots.bo.ApiResponseParameters;
import com.sella.chatbots.bo.RequestBO;
import com.sella.chatbots.bo.Response;
import com.sella.chatbots.bo.ResponseBO;
import com.sella.chatbots.enums.ACTION;

public class ChatUtils {
	
	public static ResponseBO PROCESSMSG(RequestBO input){
		String query = input.getQuery().toLowerCase();
		Response response = null;
		if(input.getParameter() !=null && input.getParameter().length()>0){
			if(input.getParameter().toLowerCase().contains("yes")){
				response = new Response(ACTION.MSG, "Request Accepted", null, false, null,null);
			}else if(input.getParameter().toLowerCase().contains("no")){
				response = new Response(ACTION.MSG, "Request Cancelled", null, false, null,null);
			}else{
				response = new Response(ACTION.MSG, "Provide Valid Response", null, false, null,null);
			}
		}else if(query.contains("welcome")){
			response = new Response(ACTION.MSG, "Hi, This is Sella Assistant", null, false, null,null);
		}else if(query.contains("bill payments")){
			List<String> suggestions = new ArrayList<String>();
			suggestions.add("ELectric Bill");
			suggestions.add("Telephone Bill");
			response = new Response(ACTION.SUGGESTION, "Please select the Merchant", suggestions, false, null,null);
		}else if(query.contains("balance")){
			response = new Response(ACTION.MSG, "your balance is 5000 euro", null, false, null,null);
		}else if(query.contains("transaction")){
			ApiResponse apiResponse = new ApiAICall().getHttpResult(query);
			ApiResponseParameters responseParameters = apiResponse.getResult().getParameters();
			response = new Response(ACTION.MSG, "your Transaction List", null, true, null,null);
			response = new TransUtil().getTransList(response,responseParameters.getFromdate(),responseParameters.getTodate(),responseParameters.getDuration(),responseParameters.getNoofduration());
		}else if(query.contains("confirm")){
			response = new Response(ACTION.CONFIRM, "Are you sure to Transfer", null, false, null,null);
		}else if(query.contains("account") || query.contains("card")){
			ApiResponse apiResponse = new ApiAICall().getHttpResult(query);
			ApiResponseParameters responseParameters = apiResponse.getResult().getParameters();
			response = new Response(ACTION.LINK, "Go to Below Link", null, false, null,null);
			response = new AccountUtil().getProductsLinks(response, responseParameters.getProducts());
		}else{
			response = new Response(ACTION.MSG, "I am bit confused,please be specific", null, false, null,null);
		}
		ResponseBO responseBO = new ResponseBO(input, input.getSessionId(), input.getLoginSessionId(), response);
		return responseBO;
		
	}
	
	private static Date stringToDate(String date_s,Boolean isToDate){
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd");
		Date date = null;
		try {
			date = dt.parse(date_s);
			if(isToDate){
				date.setHours(23);
				date.setMinutes(59);
				date.setSeconds(59);
			}else{
				date.setHours(0);
				date.setMinutes(0);
				date.setSeconds(0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		return date;
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String date_s = dt.format(date);
		return date_s;
	}

}
