package com.javainuse.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.dao.CountriesRepository;
import com.javainuse.model.Countries;
import com.javainuse.utils.Commons;
import com.javainuse.utils.JSONObj_Serial;

@RestController
@RequestMapping("/comms")
public class Commoncontrollers {

	@Autowired
	CountriesRepository countriesRepository;
	@GetMapping (value="/allContries",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getContries(){
		ResponseEntity<?> output=null;
		JSONObject jsonObject=new JSONObject();
		try {
			List<Countries> con=countriesRepository.findAll();
			
			output=new ResponseEntity<>(JSONObj_Serial.toJSONArray("data", con).toString(),HttpStatus.OK);
		} catch (Exception e) {
			jsonObject.put(Commons.MESSAGE, e.getMessage());
		output=new ResponseEntity<>(jsonObject.toString(),HttpStatus.BAD_REQUEST);
		}
		
		return output;
	}
}
