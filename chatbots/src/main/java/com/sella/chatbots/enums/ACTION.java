package com.sella.chatbots.enums;

public enum ACTION{
	
	
	MSG("MSG"),
	LOGIN("LOGIN"),
	CONFIRM("CONFIRM"),
	SUGGESTION("SUGGESTION"),
	LINK("LINK"),
	CLOSE("CLOSE");
	
	
	public final String VALUE;
	ACTION(final String value){
		VALUE = value;
	}
}
