package com.javainuse.controller;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;
import com.javainuse.service.ComplainService;
import com.javainuse.service.ComplaintActionService;
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
	

	@PostMapping("/addComplaint")
	public String addComplain(@RequestBody
		PublicComplain complain) {
		
//		if (bindingResult.hasErrors()) {
//			attributes.addFlashAttribute("error", "Error: " + bindingResult.getFieldError().getDefaultMessage());
//			return "redirect:/public/dashbord";
//		}
		try {
			PublicLogin loged = contextUsage.getLoginUSER();
			complain.setComplaintDate(new Date());
			complain.setPublicid(loged);
			complainRepository.save(complain);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		
			return "redirect:/public/dashbord";
		}

		return "redirect:/public/dashbord";

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
			
			output = new ResponseEntity<>(complaintActionService.getStatus(request.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}

}
