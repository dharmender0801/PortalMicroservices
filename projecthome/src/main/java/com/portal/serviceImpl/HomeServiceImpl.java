package com.portal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Model.HomeModel;
import com.portal.Model.MisColumnModel;
import com.portal.repos.HomeRepos;
import com.portal.repos.MisColumnRepos;
import com.portal.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeRepos hRepo;
	
	@Autowired
	private MisColumnRepos misrepos;

	@Override
	public List<HomeModel> GetdatafromList() {
		// TODO Auto-generated method stub
		return this.hRepo.findAll();
	}

	@Override
	public void addData(HomeModel hmodel) {
		hRepo.save(hmodel);

	}

	@Override
	public List<MisColumnModel> getColumn(String service) {
		// TODO Auto-generated method stub
		return misrepos.findByService(service);
	}

	@Override
	public void addColumnData(MisColumnModel misModel) {
		// TODO Auto-generated method stub
		misrepos.save(misModel);
	}

}
