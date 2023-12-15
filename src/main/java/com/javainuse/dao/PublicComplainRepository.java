package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javainuse.model.*;
import java.util.List;
import java.util.Optional;


public interface PublicComplainRepository extends JpaRepository<PublicComplain, Long> {

	@Query("SELECT pc FROM PublicComplain pc WHERE pc.countries.id = ?1")
	List<PublicComplain> findByCountry(int countyID);
	
	List<PublicComplain> findByPublicid(PublicLogin publicid);

}
