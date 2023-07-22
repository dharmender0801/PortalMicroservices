package com.portalmis.service;

import java.util.List;

import com.portalmis.request.Requests;
import com.portalmis.response.MisResponse;

public interface CallbackService {

	MisResponse getCall(List<Requests> requests);

}
