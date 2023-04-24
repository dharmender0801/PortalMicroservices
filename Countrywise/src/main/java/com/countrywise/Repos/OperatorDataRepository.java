package com.countrywise.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countrywise.Model.OperatorData;

public interface OperatorDataRepository extends JpaRepository<OperatorData, Long> {

	List<OperatorData> findByOperatorId(String operatorId);
}
