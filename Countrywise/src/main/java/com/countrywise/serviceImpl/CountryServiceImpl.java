package com.countrywise.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countrywise.Model.Country;
import com.countrywise.Model.Operator;
import com.countrywise.Model.OperatorData;
import com.countrywise.Repos.CountryRepository;
import com.countrywise.Repos.OperatorDataRepository;
import com.countrywise.Repos.OperatorRepository;
import com.countrywise.response.AllResponse;
import com.countrywise.response.OperatorResponse;
import com.countrywise.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepos;

	@Autowired
	private OperatorDataRepository opdatarepo;

	@Autowired
	private OperatorRepository oprepo;

	@Override
	public List<AllResponse> getAll() {
		List<AllResponse> response = new ArrayList<>();
		
		List<Country> data = countryRepos.findAll();

		if (data != null) {
			for (Country c : data) {
				List<OperatorResponse> operatorResponse = new ArrayList<>();
				AllResponse AllresponseModel = new AllResponse();
				AllresponseModel.setId(c.getId());
				AllresponseModel.setCountryname(c.getCountryName());
				List<Operator> operatorModel = oprepo.findByCountryId(c.getCountryId());
				for (Operator op : operatorModel) {
					OperatorResponse opresponseModel = new OperatorResponse();
					List<OperatorData> DataModel = opdatarepo.findByOperatorId(op.getOperatorId());
					opresponseModel.setOperatordata(DataModel);
					opresponseModel.setName(op.getOperator());
					operatorResponse.add(opresponseModel);

				}
				
				AllresponseModel.setOperator(operatorResponse);
				response.add(AllresponseModel);
			}

		}

		return response;

	}

}
