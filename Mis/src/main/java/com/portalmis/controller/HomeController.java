package com.portalmis.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jpmml.model.annotations.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portalmis.Model.VendorModel;
import com.portalmis.request.Requests;
import com.portalmis.response.MisResponse;
import com.portalmis.response.StatusCode;
import com.portalmis.response.VendorResponse;
import com.portalmis.service.mis_service;

@RestController
@RequestMapping("/Airtel-CongoB")
@CrossOrigin(origins = "*")
public class HomeController {

	@Autowired
	private mis_service misservice;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello World!";

	}

	@RequestMapping(method = { RequestMethod.POST }, path = "/voicechat/live")
	public ResponseEntity<MisResponse> GetData(@RequestBody List<Requests> reqBody) {

		MisResponse misResponse = new MisResponse();
		misResponse = misservice.getData(reqBody);

		return new ResponseEntity<MisResponse>(misResponse, HttpStatus.OK);
	}

	@PostMapping("/voicechat/mis")
	public ResponseEntity<MisResponse> getMis(@RequestBody List<Requests> requests) throws ParseException {

		return new ResponseEntity<MisResponse>(misservice.Mis(requests), HttpStatus.OK);
	}

	@PostMapping("/consolidate")
	public ResponseEntity ConsoldateData(@RequestBody List<Requests> reqBody) {
		misservice.GetConsolidate(reqBody);

		return new ResponseEntity<MisResponse>(HttpStatus.OK);
	}

	public ResponseEntity BillingHits(@RequestBody List<Requests> reqBody) {
		misservice.getBillingHits(reqBody);
		return null;
	}

	@GetMapping("/vendor")
	public ResponseEntity<MisResponse> VendorData() {
		MisResponse misResponse = new MisResponse();
		misResponse = misservice.GetVendorData();
		return new ResponseEntity<MisResponse>(misResponse, HttpStatus.OK);
	}

	@GetMapping("/find/vendor")
	public ResponseEntity<MisResponse> find(@RequestParam String cpid) {
		MisResponse misResponse = new MisResponse();
		misResponse = misservice.findByCpidOrName(cpid);
		return new ResponseEntity<MisResponse>(misResponse, HttpStatus.OK);
	}

	@GetMapping("/find/vendorname")
	public ResponseEntity<MisResponse> findByName(@RequestParam String name) {
		MisResponse misResponse = new MisResponse();
		misResponse = misservice.findByName(name);
		return new ResponseEntity<MisResponse>(misResponse, HttpStatus.OK);
	}

	@PutMapping("/update/vendor")
	public ResponseEntity<StatusCode> find(@RequestBody VendorModel cpid) {
		StatusCode misResponse = new StatusCode();
		misResponse = misservice.UpdateByVendor(cpid);
		return new ResponseEntity<StatusCode>(misResponse, HttpStatus.OK);
	}

	@PutMapping("/update/supress")
	public ResponseEntity<StatusCode> UpdateSupress(@RequestBody VendorModel cpid) {
		StatusCode misResponse = new StatusCode();
		misResponse = misservice.UpdateSupressLogic(cpid);
		return new ResponseEntity<StatusCode>(misResponse, HttpStatus.OK);
	}

	@PostMapping("/add/vendor")
	public ResponseEntity<VendorResponse> addVendor(@RequestBody VendorModel vendModel) {
		VendorResponse response = misservice.AddVendor(vendModel);
		return new ResponseEntity<VendorResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/get/vendor")
	public VendorModel getVendor(@RequestParam String name) {
		return misservice.getVendor(name);
	}
	
	
	@DeleteMapping("/delete/vendor")
	public ResponseEntity<VendorResponse> deleteVendor(@RequestParam String cpid)
	{
		VendorResponse response = misservice.deleteVendor(cpid);
		return new ResponseEntity<VendorResponse>(response, HttpStatus.OK);
		
	}
	

}
