package com.countrywise.response;

import java.util.List;

import com.countrywise.Model.OperatorData;

import lombok.Data;

@Data
public class OperatorResponse {

	private String name;

	private List<OperatorData> operatordata;

}
