package com.portalmis.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portalmis.Model.VendorModel;

public interface VendorRepos extends JpaRepository<VendorModel, Long> {

	VendorModel findByCpId(String cpid);

	@Query(value = "SELECT * FROM `vendor_details` ORDER BY cp_id  DESC LIMIT 1", nativeQuery = true)
	VendorModel findOrderByCpid();

	VendorModel findByVendorName(String name);

}
