package com.sella.chatbots.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sella.chatbots.bo.Transaction;
import com.sella.chatbots.bo.TranscationBO;

public class JsonToObject {

	private static JsonToObject JSONTOOBJ;
	
	private List<TranscationBO> trans;

	private JsonToObject() {
	}

	public static JsonToObject GETINSTANCE() {
		if (JSONTOOBJ == null) {
			JSONTOOBJ = new JsonToObject();
		}
		return JSONTOOBJ;
	}

	public List<TranscationBO> getTrans() {
		if (this.trans == null) {
			String json = new JsonToObject().readFileLineByLine();
			System.out.println("load JSON--------"+json);
			ObjectMapper mapper = new ObjectMapper();
			Transaction obj = null;
			try {
				obj = mapper.readValue(json, Transaction.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.trans = obj.getTranscationBOs();
		}
		return this.trans;
	}

	public String readFileLineByLine() {
//		BufferedReader buffReader = null;
//		final StringBuffer text = new StringBuffer();
//		try {
//			buffReader = new BufferedReader(new FileReader("D:\\Transjson.txt"));
//			String line = buffReader.readLine();
//			while (line != null) {
//				System.out.println(line);
//				text.append(line);
//				line = buffReader.readLine();
//			}
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		} finally {
//			try {
//				buffReader.close();
//			} catch (IOException ioe1) {
//				// Leave It
//			}
//		}
//		return text.toString();
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("transProperties.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	return prop.getProperty("trans");
	}

}
