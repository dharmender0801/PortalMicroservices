package com.portalmis.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.portalmis.Model.DailyMis;
import com.portalmis.Model.VendorModel;
import com.portalmis.repos.MisRepos;
import com.portalmis.repos.VendorRepos;
import com.portalmis.request.Requests;
import com.portalmis.response.Columns;
import com.portalmis.response.Datas;
import com.portalmis.response.MisResponse;
import com.portalmis.response.StatusCode;
import com.portalmis.response.VendorResponse;
import com.portalmis.response.count;
import com.portalmis.response.values;
import com.portalmis.service.mis_service;

@Service
public class mis_serviceImpl implements mis_service {

	// TODO Auto-generated method stub
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private VendorRepos vendorepos;
	@Autowired
	private MisRepos misRepos;

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
	public void getBillingHits(List<Requests> reqBody) {
		// TODO Auto-generated method stub

	}

	@Override
	public MisResponse GetVendorData() {
		// TODO Auto-generated method stub
		MisResponse misResponse = new MisResponse();
		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate.getForObject("http://all-services/service/get/column?service=vendor",
				List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=vendor",
				String.class);
		List<VendorModel> vendList = vendorepos.findAll();
		List<Datas> dataList = getResponseforVendor(vendList, response);
		misResponse.setColumns(arrayList);
		misResponse.setDatas(dataList);
		return misResponse;

	}

	private List<Datas> getResponseforVendor(List<VendorModel> vendList, String response) {
		// TODO Auto-generated method stub
		List<Datas> datas = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode reqNode = mapper.readTree(response);
			for (VendorModel vendor : vendList) {
				Datas data = new Datas();
				List<values> valList = new ArrayList<>();
				for (JsonNode col : reqNode) {
					values value = new values();
					if (col.get("columnName").asText().trim().equalsIgnoreCase("CP-ID")) {
						value.setName(col.get("columnName").asText());
						value.setCount(vendor.getCpId());
						valList.add(value);
						data.setValues(valList);
					}
					if (col.get("columnName").asText().trim().equalsIgnoreCase("Advertizer-Name")) {
						value.setName(col.get("columnName").asText());
						value.setCount(vendor.getVendorName());
						valList.add(value);
						data.setValues(valList);
					}
					if (col.get("columnName").asText().trim().equalsIgnoreCase("Callback-Ratio")) {
						value.setName(col.get("columnName").asText());
						value.setCount(vendor.getCut() + ":" + vendor.getCounter());
						valList.add(value);
						data.setValues(valList);
					}
					if (col.get("columnName").asText().trim().equalsIgnoreCase("Callback-Limit")) {
						value.setName(col.get("columnName").asText());
						value.setCount(vendor.getCallbackLimit());
						valList.add(value);
						data.setValues(valList);
					}
					if (col.get("columnName").asText().trim().equalsIgnoreCase("LP-Url")) {
						value.setName(col.get("columnName").asText());
						value.setCount("http://acb.friendzchat.mobi/friendzchat?cpid=" + vendor.getCpId()
								+ "&pubid=<pubid>&kpid=<kpid>");
						valList.add(value);
						data.setValues(valList);
					}
				}
				datas.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datas;
	}

	@Override
	public MisResponse Mis(List<Requests> requests) throws ParseException {
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

		List<Datas> data = findByBetweenBothDate(startDate, endDate, response);
		misResponse.setColumns(arrayList);
		misResponse.setDatas(data);
		return misResponse;
	}

	private List<Datas> findByBetweenBothDate(String startDate, String endDate, String response) {
		String query = "select * from tbl_mis_etl where date(date_time) between '" + startDate + "' and '" + endDate
				+ "' ";
		System.out.println(query);
		List<DailyMis> list = jdbcTemplate.query(query,
				(rs, rowNum) -> new DailyMis(null, rs.getLong("Active_Base"), rs.getLong("Calls"),
						rs.getLong("Duration"), rs.getLong("pulses"), rs.getLong("Total_Mou"),
						rs.getLong("Unique_User"), rs.getLong("New_Subscriptions_count"),
						rs.getLong("Repeat_Subscriptions_count"), rs.getLong("New_Subscriptions_Rev"),
						rs.getLong("Repeat_Subscriptions_Rev"), rs.getLong("Total_Sub_Revenue"),
						rs.getLong("Browse_Revenue"), rs.getLong("Total_Revenue"), rs.getLong("Sub_Revenue_USD"),
						rs.getLong("Total_Revenue_USD"), rs.getLong("Total_Unsub"), rs.getDate("date_time")));
		List<Datas> datas = new ArrayList<>();

		list.forEach(e -> {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode reqNode = null;
			try {
				reqNode = mapper.readTree(response);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Datas data = new Datas();
			List<values> valList = new ArrayList<>();
			for (JsonNode col : reqNode) {
				values value = new values();
				if (col.get("columnName").asText().equalsIgnoreCase("Date")) {
					value.setCount(e.getDateTime().toString());
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().equalsIgnoreCase("Active Base")) {
					value.setCount(String.valueOf(e.getActive_base()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Calls (Nos.)")) {
					value.setCount(String.valueOf(e.getCalls()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Duration (In Seconds)")) {
					value.setCount(String.valueOf(e.getDurations()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);

				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Pulses")) {
					value.setCount(String.valueOf(e.getPulses()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Mou's")) {
					value.setCount(String.valueOf(e.getTotalMou()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Unique User (Nos.)")) {
					value.setCount(String.valueOf(e.getUniquqUser()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("New Subscriptions")) {
					value.setCount(String.valueOf(e.getNewSubscriptionCount()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Renwals")) {
					value.setCount(String.valueOf(e.getRepeatSubscriptionCount()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("New Sub Revnue")) {
					value.setCount(String.valueOf(e.getNewSubRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Renwal Revenue")) {
					value.setCount(String.valueOf(e.getRenSubRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Sub Revenue")) {
					value.setCount(String.valueOf(e.getTotalSubRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Browse Revenue (L.C)")) {
					value.setCount(String.valueOf(e.getBrowseRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Revenue (L.C)")) {
					value.setCount(String.valueOf(e.getTotalRev()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Revenue (USD)")) {
					value.setCount(String.valueOf(e.getTotalRevUsd()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Revenue ( $ USD )")) {
					value.setCount(String.valueOf(e.getTotalRevUsd()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Total Unsub")) {
					value.setCount(String.valueOf(e.getTotalUnsub()));
					value.setName(col.get("columnName").asText());
					valList.add(value);
					data.setValues(valList);
				}

			}
			datas.add(data);

//			return datas;
		});

		return datas;
	}

	@Override
	public MisResponse findByCpid(String cpid) {
		// TODO Auto-generated method stub
		MisResponse misResponse = new MisResponse();
		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate
				.getForObject("http://all-services/service/get/column?service=updatevendor", List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=updatevendor",
				String.class);
		VendorModel vendor = vendorepos.findByCpId(cpid);
		List<Datas> data = findByCpid(response, vendor);

		misResponse.setColumns(arrayList);
		misResponse.setDatas(data);

		return misResponse;
	}

	private List<Datas> findByCpid(String response, VendorModel vendor) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		List<Datas> datas = new ArrayList<>();
		JsonNode reqNode;
		
		try {
			reqNode = mapper.readTree(response);
			Datas data = new Datas();
			List<values> valList = new ArrayList<>();
			reqNode.forEach(col -> {
				values value = new values();
				if (col.get("columnName").asText().trim().equalsIgnoreCase("CP-ID")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getCpId());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("Advertizer-Name")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getVendorName());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("CUT")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getCut());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("COUNTER")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getCounter());
					valList.add(value);
					data.setValues(valList);
				}
				if (col.get("columnName").asText().trim().equalsIgnoreCase("CALLBACK LIMIT")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getCallbackLimit());
					valList.add(value);
					data.setValues(valList);
				}

				if (col.get("columnName").asText().trim().equalsIgnoreCase("POSTBACK URL")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getCallback_url());
					valList.add(value);
					data.setValues(valList);
				}

				if (col.get("columnName").asText().trim().equalsIgnoreCase("DAILY CAPPING")) {
					value.setName(col.get("columnName").asText());
					value.setCount(vendor.getDailyCapping());
					valList.add(value);
					data.setValues(valList);
				}
			});
			datas.add(data);

		} catch (

		JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datas;
	}

	@Override
	public StatusCode UpdateByVendor(VendorModel cpid) {
		// TODO Auto-generated method stub

		StatusCode code = new StatusCode();

		VendorModel vendor = vendorepos.findByCpId(cpid.getCpId());
		if (vendor != null) {
			vendor.setCounter(cpid.getCounter());
			vendor.setCut(cpid.getCut());
			vendor.setCallbackLimit(cpid.getCallbackLimit());
			vendor.setCallback_url(cpid.getCallback_url());
			vendor.setVendorName(cpid.getVendorName());
			vendorepos.save(vendor);
			code.setCode(200);
			code.setStatusCode("Successfully Update");

		} else {
			code.setCode(500);
			code.setStatusCode("Advertiver Not Found");
		}
		return code;
	}

	@Override
	public StatusCode UpdateSupressLogic(VendorModel cpid) {
		// TODO Auto-generated method stub
		StatusCode code = new StatusCode();
		VendorModel vendor = vendorepos.findByCpId(cpid.getCpId());
		if (vendor != null) {
			vendor.setCounter(cpid.getCounter());
			vendor.setCut(cpid.getCut());
			vendor.setCallbackLimit(cpid.getCallbackLimit());
			vendorepos.save(vendor);
			code.setCode(200);
			code.setStatusCode("Successfully Update");

		} else {
			code.setCode(500);
			code.setStatusCode("Advertiver Not Found");
		}
		return code;
	}

	@Override
	public VendorResponse AddVendor(VendorModel vendModel) {
		// TODO Auto-generated method stub
		VendorResponse response = new VendorResponse();
		VendorModel veModel = vendorepos.findOrderByCpid();
		int cpid = Integer.valueOf(veModel.getCpId());
		vendModel.setCpId(String.valueOf((cpid+1)));
		vendorepos.save(vendModel);
		response.setCode(200);
		response.setStatusCode("Added Successfully");
		response.setLpUrl("http://acb.friendzchat.mobi/friendzchat?cpid=" + vendModel.getCpId() + "&pubid=<pubid>&kpid=<kpid>");
		return response;
	}

	@Override
	public MisResponse findByName(String name) {
		// TODO Auto-generated method stub
		MisResponse misResponse = new MisResponse();
		@SuppressWarnings("unchecked")
		List<Columns> arrayList = restTemplate
				.getForObject("http://all-services/service/get/column?service=updatevendor", List.class);
		String response = restTemplate.getForObject("http://all-services/service/get/column?service=updatevendor",
				String.class);
		VendorModel vendor = vendorepos.findByVendorName(name);
		List<Datas> data = findByCpid(response, vendor);

		misResponse.setColumns(arrayList);
		misResponse.setDatas(data);

		return misResponse;
		
		
	}

}
