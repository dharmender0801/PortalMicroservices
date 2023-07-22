package com.portalmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalmis.request.Requests;
import com.portalmis.response.MisResponse;
import com.portalmis.service.CallbackService;

@RestController
@RequestMapping("/Airtel-CongoB")
public class CallbackController {
	
	@Autowired
	private CallbackService callbackService;
	
	@PostMapping("/callback")
	public ResponseEntity<MisResponse> Callback(@RequestBody List<Requests> requests )
	{	
		MisResponse response = callbackService.getCall(requests);
		return new ResponseEntity<MisResponse>(response,HttpStatus.OK);
	}
	
}
