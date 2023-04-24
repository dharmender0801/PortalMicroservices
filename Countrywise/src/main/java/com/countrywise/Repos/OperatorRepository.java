package com.countrywise.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.countrywise.Model.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

	List<Operator> findByCountryId(Integer countryId);
}
