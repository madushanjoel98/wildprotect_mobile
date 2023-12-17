package com.javainuse.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.dao.AcceptedComplainsRepository;
import com.javainuse.dao.PublicComplainRepository;
import com.javainuse.dao.RejectResonsRepository;
import com.javainuse.dto.StatusDTO;
import com.javainuse.model.AcceptedComplains;
import com.javainuse.model.PublicComplain;
import com.javainuse.model.RejectResons;
import com.javainuse.service.ComplainService;
import com.javainuse.service.ComplaintActionService;
import com.javainuse.utils.UserContextUsage;





@Service
@Transactional
public class CompainActionIMPL implements ComplaintActionService {
	private final int ACCEPT = 1, REJECT = 2, PENDDING = 0;
	final Logger logger = LoggerFactory.getLogger(CompainActionIMPL.class);
	
	@Autowired
	ComplainService complainService;

	@Autowired
	PublicComplainRepository complainRepository;

	@Autowired
	UserContextUsage contextUsage;

	@Autowired
	AcceptedComplainsRepository acceptedComplainsRepository;

	@Autowired
	RejectResonsRepository rejectResonsRepository;

	@Override
	public StatusDTO getStatus(long compid) throws Exception {
		//This for Public
		StatusDTO output=new StatusDTO();
		
		try {
			PublicComplain complain = complainRepository.findById(compid)
					.orElseThrow(() -> new EntityNotFoundException("Complain not Found"));
			output.setReview_statusCode(complain.getReview_status());
			if(complain.getReview_status()==ACCEPT) {
				
				AcceptedComplains acomps=acceptedComplainsRepository.findByComplain(complain).get(0);
				output.setAccepted(acomps);
				return output;
			} else
			if(complain.getReview_status()==REJECT) {
				RejectResons rej =rejectResonsRepository.findByComplaintid(complain).get(0);
				output.setRejected(rej);
				return output;
			} 
			
			
			
		} catch (Exception e) {
			throw new Exception("Fail to get Status:" + e.getMessage());
		}
		return output;
	}

	


}
