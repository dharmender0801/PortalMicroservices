package com.portal.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Services")
public class HomeModel {
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String servicename;
	@Column
	private String routeing;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getRouteing() {
		return routeing;
	}
	public void setRouteing(String routeing) {
		this.routeing = routeing;
	}
	public HomeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomeModel(Long id, String servicename, String routeing) {
		super();
		this.id = id;
		this.servicename = servicename;
		this.routeing = routeing;
	}
	@Override
	public String toString() {
		return "HomeModel [id=" + id + ", servicename=" + servicename + ", routeing=" + routeing + "]";
	}
	
	
	
	
	

}
