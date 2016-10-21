package com.sella.chatbots.utils;

import java.util.ArrayList;
import java.util.List;

import com.sella.chatbots.bo.Response;

public class AccountUtil {
	
	public Response getProductsLinks(Response res,List<String> productNames){
		List<String> productLinks = new ArrayList<String>();
		for (String productName : productNames) {
			String productLink = PropLoad.GETINSTANCE().getProps("product").getProperty(productName);
			productLinks.add(productLink);
		}
		if(productNames == null && productNames.isEmpty()){
			res.setMessage("Sorry the product you are asking is not available.");
		}else{
			res.setLink(productLinks);
		}
		return res;
	}

}
