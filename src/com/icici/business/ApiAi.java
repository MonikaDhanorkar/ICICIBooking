package com.icici.business;

import org.codehaus.jackson.map.ObjectMapper;

import com.icici.POJOmodel.JavaModel;

public class ApiAi 
{
	public JavaModel jsonToJava(String json) {
		JavaModel apiAiResponse = null;
		try {
			System.out.println(json);
			ObjectMapper mapper= new ObjectMapper();
			apiAiResponse = mapper.readValue(json, JavaModel.class);
			System.out.println(apiAiResponse);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiAiResponse;

	}
}
