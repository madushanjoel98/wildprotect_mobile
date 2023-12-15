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

import wild.protection.controllers.admins.LoginController;
import wild.protection.dto.request.AcceptCompalinDTO;
import wild.protection.dto.request.ByIDRequest;
import wild.protection.models.PublicComplain;
import wild.protection.models.PublicLogin;
import wild.protection.repository.CountriesRepository;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.service.ComplainService;
import wild.protection.service.ComplaintActionService;
import wild.protection.service.PublicSeesionService;
import wild.protection.utils.Commoncontexts;
import wild.protection.utils.JSONObj_Serial;

@Controller
@RequestMapping("/public")
public class PublicDashboardController {
	@Autowired
	PublicSeesionService publicSeesionService;
	@Autowired
	PublicComplainRepository complainRepository;
	@Autowired
	CountriesRepository countriesRepository;
	
	@Autowired
	ComplainService complainService; 
	
	
	@Autowired
	ComplaintActionService complaintActionService;

	 Logger logger = LoggerFactory.getLogger(LoginController.class);
	@GetMapping("/dashbord")
	public String dashborad(Model model, HttpSession session, RedirectAttributes attributes) {
		if (publicSeesionService.logedpublic(session) == null) {
			attributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		model.addAttribute("allcompains", complainRepository.findByPublicid(publicSeesionService.logedpublic(session)));
		model.addAttribute("country", countriesRepository.findAll());
		model.addAttribute("complainr", new PublicComplain());
	
		model.addAttribute("user", publicSeesionService.logedpublic(session));
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/public/dashboard/dashboardmain.html");
		return "velonicpage.html";
	}

	@PostMapping("/addComplaint")
	public String addComplain(RedirectAttributes attributes, HttpSession session,
			@ModelAttribute @Valid PublicComplain complain, BindingResult bindingResult) {
		if (publicSeesionService.logedpublic(session) == null) {
			attributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute("error", "Error: " + bindingResult.getFieldError().getDefaultMessage());
			return "redirect:/public/dashbord";
		}
		try {
			PublicLogin loged = publicSeesionService.logedpublic(session);
			complain.setComplaintDate(new Date());
			complain.setPublicid(loged);
			complainRepository.save(complain);
			attributes.addFlashAttribute("success", "complain added");
		} catch (Exception e) {
			logger.error(e.getMessage());
			attributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/public/dashbord";
		}

		return "redirect:/public/dashbord";

	}

	@GetMapping(value = "/deleteComplain")
	public String delete(@RequestParam(value = "comid") long compid, RedirectAttributes redirectAttributes,
			HttpSession session) {
		if (publicSeesionService.logedpublic(session) == null) {
			redirectAttributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}
		try {
			PublicComplain complain = complainRepository.findById(compid).get();
			long comps = complain.getPublicid().getPublicid();
			if (publicSeesionService.logedpublic(session).getPublicid() != comps) {
				redirectAttributes.addFlashAttribute("error", " don't match with id");
				return "redirect:/public/dashbord";
			}
			complainRepository.deleteById(compid);
			redirectAttributes.addFlashAttribute(Commoncontexts.SUCCESS, "Comlain Successfully deleted");
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
		if (publicSeesionService.logedpublic(session) == null) {
			output = new ResponseEntity<>("ERROR user not Found", HttpStatus.BAD_REQUEST);
			return output;
		}
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
		if (publicSeesionService.logedpublic(session) == null) {
			redirectAttributes.addFlashAttribute("error", "Please Sign UP");
			return "redirect:/public/login";
		}

		try {
			// Find the complaint by its ID
			complainService.update(publicxom.getPcompId(), publicxom);
			// Set a success message
			redirectAttributes.addFlashAttribute(Commoncontexts.SUCCESS, "Complain Updated");
		} catch (Exception e) {
			logger.error(e.getMessage());
			// If an exception occurs, set an error message and redirect to the dashboard
			redirectAttributes.addFlashAttribute(Commoncontexts.ERROR, e.getMessage());
			return "redirect:/public/dashbord?";
		}

		// Redirect to the login page
		return "redirect:/public/dashbord";
	}
	@PostMapping(value = "/getStatus")
	private ResponseEntity<?> getStatus(@RequestBody ByIDRequest request, HttpSession session) {
		ResponseEntity<?> output = null;
		if (publicSeesionService.logedpublic(session) == null) {
			output = new ResponseEntity<>("ERROR user not Found", HttpStatus.BAD_REQUEST);
			return output;
		}
		try {
			
			output = new ResponseEntity<>(complaintActionService.getStatus(request.getId()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			output = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}

}
