package com.javainuse.service;

import org.springframework.stereotype.Service;

import com.javainuse.dto.request.RegisterUserDTO;
import com.javainuse.model.PublicLogin;

@Service
public interface LoginService {
public PublicLogin register(RegisterUserDTO dto) throws Exception;

}
