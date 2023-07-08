package com.portalmis.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portalmis.request.Requests;
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
	public ResponseEntity<MisResponse> getMis(@RequestBody List<Requests> requests) {
		
		
		return new ResponseEntity<MisResponse>(misservice.Mis(requests), HttpStatus.OK);
	}

	@PostMapping("/consolidate")
	public ResponseEntity ConsoldateData(@RequestBody List<Requests> reqBody) {
		misservice.GetConsolidate(reqBody);

		return new ResponseEntity<MisResponse>(HttpStatus.OK);
	}

}
