package com.countrywise.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.countrywise.Model.Country;
import com.countrywise.Model.Operator;
import com.countrywise.Model.OperatorData;
import com.countrywise.Model.VendorModel;
import com.countrywise.Repos.CountryRepository;
import com.countrywise.Repos.OperatorDataRepository;
import com.countrywise.Repos.OperatorRepository;
import com.countrywise.Repos.VendorRepos;
import com.countrywise.response.AllResponse;
import com.countrywise.response.OperatorResponse;
import com.countrywise.service.CountryService;
import com.netflix.discovery.converters.Auto;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepos;

	@Autowired
	private OperatorDataRepository opdatarepo;

	@Autowired
	private OperatorRepository oprepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private VendorRepos repos;

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

	@Override
	public List<VendorModel> getVendor(String country) {
		// TODO Auto-generated method stub
		System.out.println(country);
		String query = "SELECT * FROM vendor_menu  WHERE " + country + " = '1'";
		List<VendorModel> list = jdbcTemplate.query(query, (rs, rowNum) -> {
			VendorModel vendorModel = new VendorModel();
			vendorModel.setId(rs.getLong("id"));
			vendorModel.setMenuName(rs.getString("menu_name"));
			vendorModel.setAirtelCongoB(rs.getString("airtel_congob"));
//			vendorModel.setSafaricomKenya(rs.getString("safaricom_kenya"));
			return vendorModel;
		});

		return list;
	}

}
