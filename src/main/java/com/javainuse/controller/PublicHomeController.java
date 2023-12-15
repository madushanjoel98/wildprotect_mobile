package com.javainuse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wild.protection.models.Admin;
import wild.protection.models.PublicLogin;
import wild.protection.repository.CountriesRepository;
import wild.protection.service.PublicSeesionService;
import wild.protection.utils.Commoncontexts;
import wild.protection.utils.PublicExpections;

@Controller
@RequestMapping("/public")
public class PublicHomeController {
	@Autowired
	CountriesRepository countriesRepository;
	@Autowired
	PublicSeesionService publicSeesionService;
	
	@GetMapping("/")
	public String loadmainPage(Model model) {
		model.addAttribute(Commoncontexts.PAGE_MODEL, "/public/publichome.html");
		return "page";
	}
	@GetMapping("/register")
	public String loadmainRegisterPage(Model model) {
		model.addAttribute("reg", new PublicLogin());
		model.addAttribute("country",countriesRepository.findAll());
		model.addAttribute(Commoncontexts.PAGE_MODEL, "public/login/publicRegister.html");
		return "page";
	}
	
	@PostMapping("/register")
	public String registerpublic(@ModelAttribute PublicLogin login,RedirectAttributes attributes) {
		try {
			publicSeesionService.register(login);
		} catch (PublicExpections e) {
			attributes.addFlashAttribute("error",e.getMessage());
			return "redirect:/public/register";
		}
		return "redirect:/public/login";
	}
	@GetMapping("/login")
	public String loadmailogin(Model model,HttpSession session) {
		if (publicSeesionService.logedpublic(session) != null) {
		
			return "redirect:/public/dashbord";
		}
		model.addAttribute("reg", new PublicLogin());
		
		model.addAttribute("country",countriesRepository.findAll());
		model.addAttribute(Commoncontexts.PAGE_MODEL, "public/login/publiclogin.html");
		return "page";
	}
	@PostMapping("/login")
	public String loginpublic(@ModelAttribute PublicLogin login,RedirectAttributes attributes,HttpSession session) {
		try {
			publicSeesionService.login(session, login.getEmail(), login.getPassword());
		} catch (PublicExpections e) {
			attributes.addFlashAttribute("error",e.getMessage());
			return "redirect:/public/login";
		}
		return "redirect:/public/dashbord";
	}
	@GetMapping("/logout")
	public String logoout(RedirectAttributes attributes,HttpSession session) {
		try {
			publicSeesionService.logout(session);
		} catch (PublicExpections e) {
			attributes.addFlashAttribute("error",e.getMessage());
			return "redirect:/public/login";
		}
		return "redirect:/public/login?logout";
	}
}
