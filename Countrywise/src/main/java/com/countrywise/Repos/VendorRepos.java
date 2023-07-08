package com.countrywise.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.countrywise.Model.VendorModel;

public interface VendorRepos extends JpaRepository<VendorModel, Long> {
	@Query(value = "SELECT * FROM vendor_menu  WHERE :columnName = '1'", nativeQuery = true)
	List<VendorModel> findByColumnNameAndColumnValue(@Param("columnName") String columnName);

}
