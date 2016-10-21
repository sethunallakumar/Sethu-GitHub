package com.sella.chatbots.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.sella.chatbots.bo.Response;
import com.sella.chatbots.bo.TranscationBO;

public class TransUtil {
	
	public Response getTransList(Response res,String fromDate,String toDate,String dur,String noOfDuration){
		if(nullChecker(fromDate) && nullChecker(toDate) && nullChecker(dur)){
			res.setMessage("Provide us Valid Transaction");
		}else{
			if(!nullChecker(fromDate) && nullChecker(toDate)){
				toDate = fromDate; 
			}
			int chk= checkLimit(noOfDuration);
			res.setList(getTransList(apiStringToDate(true,fromDate,chk,dur),apiStringToDate(false,toDate,chk,dur),chk,dur));
		}
		
		return res;
	}
	
	//Filtering based on the given from date and to date
	private List<String> getTransList(Date fromDate,Date toDate,int lastTrans,String dur){
		List<String> strings = new ArrayList<String>();
		List<TranscationBO> trans =  JsonToObject.GETINSTANCE().getTrans();
		Collections.sort(trans, new TranscationBOComparator());
		for(TranscationBO tran:trans){
			
			System.out.println(tran.getTransDate()+":: 1 "+tran.getTransDate().after(fromDate)+" "+fromDate+" "+tran.getTransDate().before(toDate)+" "+toDate);
			
			if(tran.getTransDate().after(fromDate)  && tran.getTransDate().before(toDate)){
				String s = "To "+tran.getMerchName()+" as "+tran.getTranscationType()+" of "+tran.getTransAmt()+" on "+ChatUtils.dateToString(tran.getTransDate());
				strings.add(s);
			}
		}
		
		if((dur == null || dur.equalsIgnoreCase("")) && lastTrans > 0 && strings.size()>lastTrans){
			strings = strings.subList(0, lastTrans);
		}
		
		return strings;
	}
	
	public class TranscationBOComparator implements Comparator<TranscationBO> {
	    public int compare(TranscationBO p1, TranscationBO p2) {
	    	if(p1.getTransDate().after(p2.getTransDate())){
	    		return -1;
	    	}else{
	    		return 1;
	    	}
	        
	    }
	}
	
	private Date apiStringToDate(boolean isFromDate,String date_s,int noOfDuration,String dur){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			if(isFromDate){
				date = dateFormatter.parse("2016-01-01");
			}
			String date_m = PropLoad.GETINSTANCE().getProps("period").getProperty(date_s.toString());
			if(date_m != null){
				if(isFromDate){
					date.setMonth(Integer.parseInt(date_m));
				}else{
					date = dateFormatter.parse("2016-01-01");
					date.setMonth(Integer.parseInt(date_m)+1);
				}
			}else if(dur != null && !dur.equalsIgnoreCase("") && isFromDate) {
				if(noOfDuration==0){noOfDuration=1;}
				if(dur.equalsIgnoreCase("month") || dur.equalsIgnoreCase("months")){
					Calendar cal = Calendar.getInstance(); 
					cal.add(Calendar.MONTH, -noOfDuration);
					date = cal.getTime();
				}else if(dur.equalsIgnoreCase("week") || dur.equalsIgnoreCase("weeks")){
					Calendar cal = Calendar.getInstance(); 
					cal.add(Calendar.DATE, -(noOfDuration*7));
					date = cal.getTime();
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return date;
	}
	
	private int checkLimit(String limit){
		System.out.println("limit : "+limit);
		int limit_int = 0;
		try{
			limit_int =Integer.parseInt(limit);
		}catch(Exception e){}
		return limit_int;
	}
	
	private boolean nullChecker(String Checker){
		if(Checker == null || Checker.equalsIgnoreCase("")){
			return true;
		}
		return false;
	}

	
	
}
