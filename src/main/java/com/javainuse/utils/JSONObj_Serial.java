package com.javainuse.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONObj_Serial {
public static JSONObject toJSONObject(String key,Object object) throws JsonProcessingException {
	  JSONObject jsonObject = new JSONObject();
	  ObjectMapper objectMapper = new ObjectMapper();
      String jsonObjectString = objectMapper.writeValueAsString(object);
      jsonObject.put(key, new JSONObject(jsonObjectString));
	return jsonObject;
      
}

public static JSONObject toJSONArray(String key,List<?> object) throws JsonProcessingException {
	  JSONObject jsonObject = new JSONObject();
	  ObjectMapper objectMapper = new ObjectMapper();
    String jsonObjectString = objectMapper.writeValueAsString(object);
    jsonObject.put(key, new JSONArray(jsonObjectString));
	return jsonObject;
    
}
public String objMaping(Object object) throws JsonProcessingException {
	 ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.writeValueAsString(object);
}
}
