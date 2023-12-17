package com.javainuse.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javainuse.dao.CountriesRepository;
import com.javainuse.dao.PublicComplainRepository;
import com.javainuse.dto.ByIDRequest;
import com.javainuse.dto.request.AddComplainDTO;
import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;
import com.javainuse.service.ComplainService;
import com.javainuse.service.ComplaintActionService;
import com.javainuse.utils.Commons;
import com.javainuse.utils.JSONObj_Serial;
import com.javainuse.utils.UserContextUsage;



@RestController
@RequestMapping("/public")
public class PublicDashboardController {
	
	@Autowired
	PublicComplainRepository complainRepository;
	@Autowired
	CountriesRepository countriesRepository;
	
	@Autowired
	ComplainService complainService; 
	@Autowired
	UserContextUsage contextUsage;
	
	@Autowired
	ComplaintActionService complaintActionService;

	 Logger logger = LoggerFactory.getLogger(PublicDashboardController.class);
	

	@PostMapping(value = "/addComplaint",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> addComplain(@Valid @RequestBody
		AddComplainDTO complain,BindingResult bindingResult) {
		ResponseEntity<?> output=null;
		JSONObject object=new JSONObject();
		if (bindingResult.hasErrors()) {
			object.put(Commons.MESSAGE, bindingResult.getFieldError().getDefaultMessage());
			output=new ResponseEntity(object.toString(), HttpStatus.BAD_REQUEST);
			return output;
		}
		try {
			complainService.addComplain(complain, contextUsage.getLoginUSER());
			object.put(Commons.MESSAGE, "Complain added Successfully");
			output=new ResponseEntity(object.toString(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			object.put(Commons.MESSAGE, e.getMessage());
			output=new ResponseEntity(object.toString(), HttpStatus.BAD_REQUEST);
		
		}

		return output;

	}

	@GetMapping(value = "/deleteComplain")
	public String delete(@RequestParam(value = "comid") long compid, RedirectAttributes redirectAttributes,
			HttpSession session) {
	
		try {
			PublicComplain complain = complainRepository.findById(compid).get();
			long comps = complain.getPublicid().getPublicid();
			if (contextUsage.getLoginUSER().getPublicid() != comps) {
				redirectAttributes.addFlashAttribute("error", " don't match with id");
				return "redirect:/public/dashbord";
			}
			complainRepository.deleteById(compid);
			//redirectAttributes.addFlashAttribute(Commoncontexts.SUCCESS, "Comlain Successfully deleted");
		} catch (Exception e) {
			logger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/public/dashbord?";
		}

		return "redirect:/public/dashbord?";
	}

	@PostMapping(value = "/getbyComplainID")
	private ResponseEntity<?> findbyComlainID(@RequestBody ByIDRequest request, HttpSession session) {
		ResponseEntity<?> output = null;
	
		try {
			PublicComplain complain = complainRepository.findById(request.getId()).get();

			output = new ResponseEntity<>(complain, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}

	@PostMapping(value = "/updateComplain")
	private String updateComplain(@ModelAttribute PublicComplain publicxom, HttpSession session,
			RedirectAttributes redirectAttributes) {
		// Check if the user is logged in
		

		try {
			// Find the complaint by its ID
			complainService.update(publicxom.getPcompId(), publicxom);
			// Set a success message
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			// If an exception occurs, set an error message and redirect to the dashboard
			
			return "redirect:/public/dashbord?";
		}

		// Redirect to the login page
		return "redirect:/public/dashbord";
	}
	@PostMapping(value = "/getStatus")
	private ResponseEntity<?> getStatus(@RequestBody ByIDRequest request) {
		ResponseEntity<?> output = null;
		
		try {
			
			//output = new ResponseEntity<>(JSONObj_Serial.toJSONObject("data",  complaintActionService.getStatus(request.getId()).to, HttpStatus.OK));
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}
	@PostMapping(value = "/myComplains",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> myComplains() {
		ResponseEntity<?> output = null;
	
		try {
			
   List<PublicComplain> mylist=complainRepository.findByPublicid(contextUsage.getLoginUSER());
			output = new ResponseEntity<>(JSONObj_Serial.toJSONArray("data", mylist).toString(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}
}
