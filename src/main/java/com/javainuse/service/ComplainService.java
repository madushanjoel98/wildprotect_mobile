package com.javainuse.service;

import org.springframework.stereotype.Service;

import com.javainuse.dto.request.AddComplainDTO;
import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;

import java.util.List;

@Service
public interface ComplainService {
	public void addComplain(AddComplainDTO newComplain,PublicLogin user)throws Exception;
    List<PublicComplain> getPublicComplainbyCountry(int id) throws Exception;
    List<PublicComplain> getALLCompains() throws Exception;
    public void update(long complainId,PublicComplain newComplain) throws Exception;
}
