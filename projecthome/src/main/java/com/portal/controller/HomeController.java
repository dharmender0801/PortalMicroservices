package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.Model.HomeModel;
import com.portal.Model.MisColumnModel;
import com.portal.service.HomeService;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class HomeController {

	@Autowired
	private HomeService hservice;

	@GetMapping("/check")
	public String test() {
		return "Welcome";
	}

	@GetMapping("/get")
	public ResponseEntity<List<HomeModel>> getList() {
		List<HomeModel> list = hservice.GetdatafromList();
		return new ResponseEntity<List<HomeModel>>(list, HttpStatus.OK);

	}

	@GetMapping("/add")
	public String add(@RequestBody HomeModel hmodel) {
		hservice.addData(hmodel);
		return "Success";

	}
	
	
	@GetMapping("/get/column")
	public ResponseEntity<List<MisColumnModel>> getColumn(@RequestParam("service") String service  )
	{
		List<MisColumnModel> response = hservice.getColumn(service);
		
		return new ResponseEntity<List<MisColumnModel>>(response, HttpStatus.OK);
	}
	@GetMapping("/add/column")
	public String addColumn(@RequestBody MisColumnModel misModel)
	{
		hservice.addColumnData(misModel);
		return "Success";
		
	}

}
