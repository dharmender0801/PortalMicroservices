package com.portalmis.service;

import java.text.ParseException;
import java.util.List;

import com.portalmis.Model.VendorModel;
import com.portalmis.request.Requests;
import com.portalmis.response.MisResponse;
import com.portalmis.response.StatusCode;
import com.portalmis.response.VendorResponse;

public interface mis_service {

	

	void GetConsolidate(List<Requests> reqBody);

	MisResponse getData(List<Requests> reqBody);

	void getBillingHits(List<Requests> reqBody);

	MisResponse GetVendorData();

	MisResponse Mis(List<Requests> requests) throws ParseException;

	MisResponse findByCpid(String cpid);

	StatusCode UpdateByVendor(VendorModel cpid);

	StatusCode UpdateSupressLogic(VendorModel cpid);

	VendorResponse AddVendor(VendorModel vendModel);

	MisResponse findByName(String name);

	MisResponse findByCpidOrName(String cpid);

	VendorModel getVendor(String name);

	VendorResponse deleteVendor(String cpid);

	

}
