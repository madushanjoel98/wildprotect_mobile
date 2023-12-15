package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javainuse.model.*;


public interface CountriesRepository extends JpaRepository<Countries, Integer> {
}
