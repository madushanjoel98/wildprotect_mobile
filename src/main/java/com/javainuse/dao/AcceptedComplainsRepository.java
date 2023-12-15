package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.model.*;

import java.util.List;




public interface AcceptedComplainsRepository extends JpaRepository<AcceptedComplains, Long> {
	List<AcceptedComplains> findByComplain(PublicComplain complain);
}
