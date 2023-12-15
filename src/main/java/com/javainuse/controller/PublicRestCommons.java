package com.javainuse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import wild.protection.models.PublicComplain;
import wild.protection.repository.CountriesRepository;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.service.PublicSeesionService;

@RestController
@RequestMapping("/public")
public class PublicRestCommons {
	@Autowired
	PublicSeesionService publicSeesionService;
	@Autowired
	PublicComplainRepository complainRepository;
	@Autowired
	CountriesRepository countriesRepository;
	
	@GetMapping(value = "/getAllComplainbyUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllComplainbyUser(HttpSession session) {
		ResponseEntity<Object> output = null;

		try {
			if (publicSeesionService.logedpublic(session) == null) {
				throw new Exception("user not found");
			}
			
List<PublicComplain> pubs=complainRepository.findByPublicid(publicSeesionService.logedpublic(session));
		JSONObject ob=new JSONObject();
		ob.put("g",new JSONArray( pubs));
			
			output = new ResponseEntity<Object>(ob.toString(),
					HttpStatus.OK);

		} catch (Exception e) {
			output = new ResponseEntity("Error:"+e.getMessage(),
					HttpStatus.NOT_FOUND);
		}
		return output;
	}
}
