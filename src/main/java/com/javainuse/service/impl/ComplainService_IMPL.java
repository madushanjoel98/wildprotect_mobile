package com.javainuse.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.dao.CountriesRepository;
import com.javainuse.dao.PublicComplainRepository;
import com.javainuse.dto.request.AddComplainDTO;
import com.javainuse.model.Countries;
import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;
import com.javainuse.service.ComplainService;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class ComplainService_IMPL implements ComplainService {
	final Logger logger = LoggerFactory.getLogger(ComplainService_IMPL.class);
	@Autowired
	PublicComplainRepository complainRepository;

	@Autowired
	CountriesRepository countriesRepository;

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
			if (complain.getReview_status() != 0) {
				throw new Exception("Status is not pending we can't Edit the Complain ");
			}
			// Update the complaint properties
			complain.setComplain(newComplain.getComplain());
			complain.setCountries(newComplain.getCountries());
			complain.setTitle(newComplain.getTitle());
			complain.setLocationDetails(newComplain.getLocationDetails());
		} catch (Exception e) {
			throw new Exception("Edit Error:" + e.getMessage());
		}

	}

	@Override
	public void addComplain(AddComplainDTO newComplain, PublicLogin user) throws Exception {
		try {
			Countries country = countriesRepository.findById(newComplain.getCountryID()).get();
			PublicComplain newCom = new PublicComplain();
			newCom.setPublicid(user);
			newCom.setCountries(country);
			newCom.setTitle(newComplain.getTitle());
			newCom.setComplain(newComplain.getComplain());
			newCom.setLocationDetails(newComplain.getLocationDetails());
			newCom.setComplaintDate(new Date());
			complainRepository.save(newCom);
			logger.info("Complain added by:"+user.getEmail());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
