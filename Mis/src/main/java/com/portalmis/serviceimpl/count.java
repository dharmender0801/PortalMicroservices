package com.portalmis.serviceimpl;

import java.sql.Date;

public class count {

	private Date date;
	private int calls;
	private int Duration;
	private int mou;
	private int pulses;
	private int new_sub;
	private int renwal;
	private int newSubRev;
	private int RenRev;
	private int browseRev;
	private int totalSubRev;
	private int total_unsub;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCalls() {
		return calls;
	}

	public count() {
		super();
		// TODO Auto-generated constructor stub
	}

	public count(Date date, int calls, int duration, int mou, int pulses, int new_sub, int renwal, int newSubRev,
			int renRev, int browseRev, int totalSubRev, int total_unsub) {
		super();
		this.date = date;
		this.calls = calls;
		Duration = duration;
		this.mou = mou;
		this.pulses = pulses;
		this.new_sub = new_sub;
		this.renwal = renwal;
		this.newSubRev = newSubRev;
		RenRev = renRev;
		this.browseRev = browseRev;
		this.totalSubRev = totalSubRev;
		this.total_unsub = total_unsub;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	public int getDuration() {
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	public int getMou() {
		return mou;
	}

	public void setMou(int mou) {
		this.mou = mou;
	}

	public int getPulses() {
		return pulses;
	}

	public void setPulses(int pulses) {
		this.pulses = pulses;
	}

	public int getNew_sub() {
		return new_sub;
	}

	public void setNew_sub(int new_sub) {
		this.new_sub = new_sub;
	}

	public int getRenwal() {
		return renwal;
	}

	public void setRenwal(int renwal) {
		this.renwal = renwal;
	}

	public int getNewSubRev() {
		return newSubRev;
	}

	public void setNewSubRev(int newSubRev) {
		this.newSubRev = newSubRev;
	}

	public int getRenRev() {
		return RenRev;
	}

	public void setRenRev(int renRev) {
		RenRev = renRev;
	}

	public int getBrowseRev() {
		return browseRev;
	}

	public void setBrowseRev(int browseRev) {
		this.browseRev = browseRev;
	}

	public int getTotalSubRev() {
		return totalSubRev;
	}

	public void setTotalSubRev(int totalSubRev) {
		this.totalSubRev = totalSubRev;
	}

	public int getTotal_unsub() {
		return total_unsub;
	}

	public void setTotal_unsub(int total_unsub) {
		this.total_unsub = total_unsub;
	}

}
