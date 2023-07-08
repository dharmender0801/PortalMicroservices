package com.countrywise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countrywise.Model.VendorModel;
import com.countrywise.response.AllResponse;
import com.countrywise.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService Conservice;

	@GetMapping("/country")
	public String check() {
		return "Welcom";
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AllResponse>> GetData() {
		List<AllResponse> response =  Conservice.getAll();
		
		return new ResponseEntity<List<AllResponse>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/get/vendor")
	public ResponseEntity<List<VendorModel>> getVendorData(@RequestParam("country") String country)
	{
		return new ResponseEntity<List<VendorModel>>(Conservice.getVendor(country), HttpStatus.OK);
	}
	
	
}
