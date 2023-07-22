package com.portalmis.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portalmis.Model.CallbackVendor;
import com.portalmis.request.Requests;
import com.portalmis.response.Columns;
import com.portalmis.response.Datas;
import com.portalmis.response.MisResponse;
import com.portalmis.response.count;
import com.portalmis.response.values;
import com.portalmis.service.CallbackService;

@Service
public class callbackserviceImpl implements CallbackService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${callback}")
	private String callback;

	@Override
	public MisResponse getCall(List<Requests> requests) {

		MisResponse misResponse = new MisResponse();

		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate.getForObject("http://all-services/service/get/column?service=callback",
				List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=callback",
				String.class);
		String startDate = null;
		String endDate = null;
		for (Requests req : requests) {
			if (req.getName().equalsIgnoreCase("startdate")) {
				startDate = req.getValue();

			}
			if (req.getName().equalsIgnoreCase("enddate")) {
				endDate = req.getValue();

			}

		}

		List<CallbackVendor> countList = getlistfromStartAndendDate(startDate, endDate);
		List<Datas> datasList = null;
		try {
			datasList = getdatabyDate(startDate, endDate, response, countList);
		} catch (JsonProcessingException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		misResponse.setColumns(arrayList);
		misResponse.setDatas(datasList);

		return misResponse;

	}

	private List<Datas> getdatabyDate(String startDate, String endDate, String response, List<CallbackVendor> countList)
			throws JsonMappingException, JsonProcessingException, InterruptedException {
		// TODO Auto-generated method stub
		List<Datas> datas = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode reqNode = mapper.readTree(response);
		countList.forEach(e -> {
			Datas data = new Datas();
			List<values> valList = new ArrayList<>();
			reqNode.forEach(col -> {
				values value = new values();
				if (col.get("columnName").asText().equalsIgnoreCase("Date")) {
					value.setCount(e.getDate());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().equalsIgnoreCase("Advertizer Name")) {
					value.setCount(e.getVendor());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().equalsIgnoreCase("Shared")) {
					value.setCount(e.getShared());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().equalsIgnoreCase("Suppress")) {
					value.setCount(e.getSupress());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().equalsIgnoreCase("Total")) {
					value.setCount(String.valueOf(e.getTotalCount()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
			});
			datas.add(data);
		});
		return datas;
	}

	private List<CallbackVendor> getlistfromStartAndendDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		String query = callback;
		query = query.replace("2023-07-01", startDate);
		query = query.replace("2023-07-21", endDate);
		System.out.println(query);
		List<CallbackVendor> callbackVendors = jdbcTemplate.query(query,
				(rs, rowNum) -> new CallbackVendor(rs.getLong("total_count"), rs.getString("cpid"),
						rs.getString("date"), rs.getString("vendor"), rs.getString("shared"), rs.getString("supress")));
		return callbackVendors;
	}

}
