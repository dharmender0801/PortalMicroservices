package com.countrywise.service;

import java.util.List;

import com.countrywise.Model.VendorModel;
import com.countrywise.response.AllResponse;

public interface CountryService {

	List<AllResponse> getAll();

	List<VendorModel> getVendor(String country);

}
