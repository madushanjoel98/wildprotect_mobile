package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javainuse.model.*;
import java.util.List;



public interface RejectResonsRepository extends JpaRepository<RejectResons, Long> {
	List<RejectResons> findByComplaintid(PublicComplain complaintid);
}
