package com.portal.service;

import java.util.List;

import com.portal.Model.HomeModel;
import com.portal.Model.MisColumnModel;

public interface HomeService {

	List<HomeModel> GetdatafromList();

	void addData(HomeModel hmodel);

	List<MisColumnModel> getColumn(String service);

	void addColumnData(MisColumnModel misModel);

}
