package com.portalmis.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portalmis.controller.MisResponse;
import com.portalmis.request.Requests;
import com.portalmis.service.mis_service;

@Service
public class mis_serviceImpl implements mis_service {

	// TODO Auto-generated method stub
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${querydata}")
	private String querydata;

	int activeBase;
	private Logger logger = LoggerFactory.getLogger(mis_serviceImpl.class);

	@Override
	public MisResponse getData(List<Requests> reqBody) {
		// TODO Auto-generated method stub
		MisResponse misResponse = new MisResponse();

		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate.getForObject("http://all-services/service/get/column?service=voicechat",
				List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=voicechat",
				String.class);
		String startDate = null;
		String endDate = null;
		for (Requests req : reqBody) {
			if (req.getName().equalsIgnoreCase("startdate")) {
				startDate = req.getValue();

			}
			if (req.getName().equalsIgnoreCase("enddate")) {
				endDate = req.getValue();

			}

		}

		List<count> countList = getlistfromStartAndendDate(startDate, endDate);
		List<Datas> datasList = null;
		try {
			datasList = getdatabyDate(startDate, endDate.toString(), response, countList);
		} catch (JsonProcessingException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		misResponse.setColumns(arrayList);
		misResponse.setDatas(datasList);

		return misResponse;

	}

	private List<count> getlistfromStartAndendDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		String sql = querydata;
		sql = sql.replace("2023-03-05", startDate);
		sql = sql.replace("2023-03-30", endDate);
		System.out.println(sql);
		List<count> countList = jdbcTemplate.query(sql,
				(rs, rowNum) -> new count(rs.getDate(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
						rs.getInt(12)

				));
		String basesql = "SELECT COUNT(1) FROM tbl_voicechat WHERE DATE(last_billed_date)>=DATE(SUBDATE(NOW(),30))";
		int base = jdbcTemplate.queryForObject(basesql, int.class);
		activeBase = base;
		return countList;
	}

	private List<Datas> getdatabyDate(String setdate, String enddate, String response, List<count> countList)
			throws JsonMappingException, JsonProcessingException, InterruptedException {
		// TODO Auto-generated method stub
		List<Datas> datas = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode reqNode = mapper.readTree(response);
		for (count list : countList) {
			Datas data = new Datas();
			List<values> valList = new ArrayList<>();
			for (JsonNode col : reqNode) {
				values value = new values();
				if (col.get("columnName").asText().equalsIgnoreCase("Date")) {
					value.setCount(list.getDate().toString());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().equalsIgnoreCase("Active Base")) {
					value.setCount(String.valueOf(activeBase));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Calls (Nos.)")) {
					value.setCount(String.valueOf(list.getCalls()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Duration (In Seconds)")) {
					value.setCount(String.valueOf(list.getDuration()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Pulses")) {
					value.setCount(String.valueOf(list.getPulses()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Mou's")) {
					value.setCount(String.valueOf(list.getMou()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Unique User (Nos.)")) {
					value.setCount(String.valueOf(list.getCalls()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("New Subscriptions")) {
					value.setCount(String.valueOf(list.getNew_sub()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Renwals")) {
					value.setCount(String.valueOf(list.getRenwal()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("New Sub Revnue")) {
					value.setCount(String.valueOf(list.getNewSubRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Renwal Revenue")) {
					value.setCount(String.valueOf(list.getRenRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Sub Revenue")) {
					value.setCount(String.valueOf((list.getRenRev() + list.getNewSubRev())));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Browse Revenue (L.C)")) {
					value.setCount(String.valueOf(list.getBrowseRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Revenue (L.C)")) {
					value.setCount(String.valueOf((list.getBrowseRev() + list.getNewSubRev() + list.getRenRev())));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Revenue (USD)")) {
					value.setCount(String.valueOf(((list.getRenRev() + list.getNewSubRev()) / 619)));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Revenue ( $ USD )")) {
					value.setCount(
							String.valueOf(((list.getBrowseRev() + list.getRenRev() + list.getNewSubRev()) / 619)));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Unsub")) {
					value.setCount(String.valueOf(list.getTotal_unsub()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}

			}
			datas.add(data);
		}

		return datas;
	}

	@Override
	public void GetConsolidate(List<Requests> reqBody) {
		// TODO Auto-generated method stub

	}

	@Override
	public MisResponse Mis(List<Requests> requests) {
		// TODO Auto-generated method stub

		MisResponse misResponse = new MisResponse();

		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate.getForObject("http://all-services/service/get/column?service=voicechat",
				List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=voicechat",
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
		misResponse.setColumns(arrayList);
		return null;
	}

}
