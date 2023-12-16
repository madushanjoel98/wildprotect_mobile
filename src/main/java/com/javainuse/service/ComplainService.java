package com.javainuse.service;

import org.springframework.stereotype.Service;

import com.javainuse.model.PublicComplain;

import java.util.List;

@Service
public interface ComplainService {
    List<PublicComplain> getPublicComplainbyCountry(int id) throws Exception;
    List<PublicComplain> getALLCompains() throws Exception;
    public void update(long complainId,PublicComplain newComplain) throws Exception;
}
