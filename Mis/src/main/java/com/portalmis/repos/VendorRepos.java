package com.portalmis.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portalmis.Model.VendorModel;

public interface VendorRepos extends JpaRepository<VendorModel, Long> {

	VendorModel findByCpId(String cpid);

	@Query(value = "SELECT * FROM `vendor_details` ORDER BY cp_id  DESC LIMIT 1", nativeQuery = true)
	VendorModel findOrderByCpid();

	List<VendorModel> findByVendorName(String name);
	
	@Query(value = "SELECT * FROM vendor_details v WHERE v.vendor_name LIKE %:name% OR v.cp_id LIKE %:name1%", nativeQuery = true)
    List<VendorModel> findByVendorNameOrCpIdContaining(@Param("name") String name, @Param("name1") String name1);

}
