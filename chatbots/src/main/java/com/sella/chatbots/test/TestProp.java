package com.sella.chatbots.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.sella.chatbots.utils.JsonToObject;
import com.sun.faces.util.CollectionsUtils;

public class TestProp {
	
	public static void main(String[] args){
		List<String> strings = new ArrayList<String>();
		strings.add("dfsdf");
		strings.add("dfsdf");
		strings.add("dfsdf");
		strings.add("dfsdf");
		strings.add("dfsdf");
		strings = strings.subList(0, 2);
		System.out.println(strings.size());
		//JsonToObject.GETINSTANCE().getTrans();
	}

}
