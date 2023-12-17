package com.javainuse.service.impl;

import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.dao.CountriesRepository;
import com.javainuse.dao.PublicLoginRepository;
import com.javainuse.dto.request.RegisterUserDTO;
import com.javainuse.model.Countries;
import com.javainuse.model.PublicLogin;
import com.javainuse.service.LoginService;



@Service
public class LoginServiceIMPL implements LoginService{
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private PublicLoginRepository loginRepository;
	
	@Autowired
	CountriesRepository countriesRepository;
	@Override
	public PublicLogin register(RegisterUserDTO dto) throws Exception {
		PublicLogin output=new PublicLogin();
		try {
			Optional<PublicLogin> logse = loginRepository.findByEmail(dto.getEmail());

			if (logse.isPresent()) {
				throw new Exception("User already Exits");
			}
			Countries country = countriesRepository.findById(dto.getCountryID()).get();
			output.setCountryid(country);
			output.setMobilenumber(dto.getMobilenumber());
			output.setEmail(dto.getEmail().toLowerCase());
			output.setPassword(bcryptEncoder.encode(dto.getPassword()));
			loginRepository.save(output);
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		return output;
	}

}
