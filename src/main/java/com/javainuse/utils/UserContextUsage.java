package com.javainuse.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javainuse.dao.PublicLoginRepository;
import com.javainuse.model.PublicComplain;
import com.javainuse.model.PublicLogin;


@Service
public class UserContextUsage {
	Logger logger = LoggerFactory.getLogger(UserContextUsage.class);
	
	@Autowired
	PublicLoginRepository userRepository;
	public  PublicLogin getLoginUSER() {
		PublicLogin output=null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			Optional<PublicLogin> u = userRepository.findByEmail(auth.getName());
			output=u.get();
		}
		
		return output;
	}
	public void functionUsageLogger(String message) {
    logger.info(getLoginUSER().getEmail()+":"+message);
		
	}
//	public void isHasAccess(int module) throws NoAccessExpection, FAExpectation {
//		 	user_Service.throwsAccess(getLoginUSER(), module);
//	}
	
}
