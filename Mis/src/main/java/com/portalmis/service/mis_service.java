package com.portalmis.service;

import java.util.List;

import com.portalmis.controller.MisResponse;
import com.portalmis.request.Requests;

public interface mis_service {

	

	void GetConsolidate(List<Requests> reqBody);

	MisResponse getData(List<Requests> reqBody);

}
