package com.portal.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.Model.MisColumnModel;

public interface MisColumnRepos extends JpaRepository<MisColumnModel, Long> {

	List<MisColumnModel> findByService(String service);

}
