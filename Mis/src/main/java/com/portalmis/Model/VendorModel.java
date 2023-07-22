package com.portalmis.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_details")
public class VendorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "callback_limit")
	private String callbackLimit;
	@Column(name = "callback_url")
	private String callback_url;
	@Column(name = "counter")
	private String counter;
	@Column(name = "cp_id")
	private String cpId;
	@Column(name = "cut")
	private String cut;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "daily_capping")
	private String dailyCapping;

	public String getDailyCapping() {
		return dailyCapping;
	}

	public void setDailyCapping(String dailyCapping) {
		this.dailyCapping = dailyCapping;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCallbackLimit() {
		return callbackLimit;
	}

	public void setCallbackLimit(String callbackLimit) {
		this.callbackLimit = callbackLimit;
	}

	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

}
