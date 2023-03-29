package com.portal.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.Model.HomeModel;

public interface HomeRepos extends JpaRepository<HomeModel,Long>{

	List<HomeModel> findAll();

}
