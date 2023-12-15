package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javainuse.model.*;

public interface AdminTypeRespos extends JpaRepository<AdminTypes, Integer>{

//INSERT INTO `admin_types` (`name`) VALUES
//('Master'),('Minor');

	
	
}
