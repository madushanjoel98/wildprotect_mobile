package com.javainuse.controller;

import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.javainuse.service.JwtUserDetailsService;
import com.javainuse.service.LoginService;
import com.javainuse.utils.Commons;
import com.javainuse.utils.JSONObj_Serial;
import com.javainuse.config.JwtTokenUtil;
import com.javainuse.dao.PublicLoginRepository;
import com.javainuse.dto.request.RegisterUserDTO;
import com.javainuse.model.JwtRequest;
import com.javainuse.model.JwtResponse;
import com.javainuse.model.PublicLogin;
import com.javainuse.model.UserDTO;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private PublicLoginRepository loginRepository;
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			
			final String token = jwtTokenUtil.generateToken(userDetails);
			JwtResponse response = new JwtResponse(token);
			response.setLogin(loginRepository.findByEmail(authenticationRequest.getUsername()).get());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			JSONObject obj = new JSONObject();
			obj.put(Commons.MESSAGE, e.getMessage());
			return new ResponseEntity(obj.toString(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterUserDTO user, BindingResult bindingResult)
			throws Exception {
		ResponseEntity<?> output = null;
		JSONObject obj = new JSONObject();
		try {
			if (bindingResult.hasErrors()) {
				obj.put(Commons.MESSAGE, bindingResult.getFieldError().getDefaultMessage());
				output = new ResponseEntity(obj.toString(), HttpStatus.BAD_REQUEST);
				return output;
			}
			PublicLogin log = loginService.register(user);
			obj.put(Commons.MESSAGE, "Register Success");
			obj.put("data", JSONObj_Serial.toJSONObject("user", log));
			output = new ResponseEntity(obj.toString(), HttpStatus.OK);
		} catch (Exception e) {
			obj.put(Commons.MESSAGE, e.getMessage());
			output = new ResponseEntity(obj.toString(), HttpStatus.BAD_REQUEST);
		}
		return output;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}