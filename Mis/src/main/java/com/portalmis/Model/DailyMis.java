package com.portalmis.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name="tbl_mis_etl")
@Data
@AllArgsConstructor
public class DailyMis {
	@Id
	private Long id;
	@Column(name="Active_Base")
	private long active_base;
	@Column(name="Calls")
	private long calls;
	@Column(name="Duration")
	private long durations;
	@Column(name="pulses")
	private long pulses;
	@Column(name="Total_Mou")
	private long totalMou;
	@Column(name="Unique_User")
	private long uniquqUser;
	@Column(name="New_Subscriptions_count")
	private long newSubscriptionCount;
	@Column(name="Repeat_Subscriptions_count")
	private long repeatSubscriptionCount;
	@Column(name="New_Subscriptions_Rev")
	private long newSubRev;
	@Column(name="Repeat_Subscriptions_Rev")
	private long RenSubRev;
	@Column(name="Total_Sub_Revenue")
	private long totalSubRev;
	@Column(name="Browse_Revenue")
	private long browseRev;
	@Column(name="Total_Revenue")
	private long totalRev;
	@Column(name="Sub_Revenue_USD")
	private long subRevUsd;
	@Column(name="Total_Revenue_USD")
	private long totalRevUsd;
	@Column(name="Total_Unsub")
	private long totalUnsub;
	@Column
	private Date dateTime;

}
