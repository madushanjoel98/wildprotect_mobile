package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javainuse.model.*;
import java.util.List;
import java.util.Optional;



public interface PublicLoginRepository extends JpaRepository<PublicLogin, Long> {
	Optional<PublicLogin> findByEmail(String email);
}
