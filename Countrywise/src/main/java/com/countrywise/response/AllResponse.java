package com.countrywise.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllResponse {
	
	private Long id;
	private String countryname;
	private List<OperatorResponse> operator;
}
