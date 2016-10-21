package com.sella.chatbots.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropLoad {
	
	private static PropLoad PROPLOAD;
	
	private Properties prop;


	public static PropLoad GETINSTANCE() {
		if (PROPLOAD == null) {
			PROPLOAD = new PropLoad();
		}
		return PROPLOAD;
	}
	
	public String getPropertyFile( String type ) {
		String fileName = null;
		if("period".equalsIgnoreCase(type)) {
			fileName = "periodProperties.properties";
		}else if("product".equalsIgnoreCase(type)) {
			fileName = "productProperties.properties";
		}
		return fileName;
	}
	
	public Properties getProps(String type) {
		if(this.prop == null){
			prop = new Properties();
			InputStream input = null;
			try {
				// load a properties file
				prop.load(getClass().getClassLoader().getResourceAsStream(getPropertyFile(type)));
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this.prop;
	}
	

}
