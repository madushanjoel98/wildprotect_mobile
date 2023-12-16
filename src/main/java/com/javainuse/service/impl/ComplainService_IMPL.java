package com.javainuse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.dao.PublicComplainRepository;
import com.javainuse.model.PublicComplain;
import com.javainuse.service.ComplainService;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ComplainService_IMPL implements ComplainService {

	@Autowired
	PublicComplainRepository complainRepository;
    @Override
    public List<PublicComplain> getPublicComplainbyCountry(int id) throws Exception {
        return null;
    }

    @Override
    public List<PublicComplain> getALLCompains() throws Exception {
        return null;
    }

	@Override
	public void update(long complainId, PublicComplain newComplain) throws Exception {
		try {
			PublicComplain complain = complainRepository.findById(complainId)
					.orElseThrow(() -> new EntityNotFoundException("Complain not Found"));
			if(complain.getReview_status()!=0) 
			{
				throw new Exception("Status is not pending we can't Edit the Complain ");
			}
			// Update the complaint properties
			complain.setComplain(newComplain.getComplain());
			complain.setCountries(newComplain.getCountries());
			complain.setTitle(newComplain.getTitle());
			complain.setLocationDetails(newComplain.getLocationDetails());
		} catch (Exception e) {
			throw new Exception("Edit Error:"+e.getMessage());
		}
		
	}
}
