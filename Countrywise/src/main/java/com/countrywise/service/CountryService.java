package com.countrywise.service;

import java.util.List;

import com.countrywise.Model.VendorModel;
import com.countrywise.response.AllResponse;
import com.countrywise.response.vendorResponse;

public interface CountryService {

	List<AllResponse> getAll();

	List<vendorResponse> getVendor(String country);

}
