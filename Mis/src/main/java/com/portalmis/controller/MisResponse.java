package com.portalmis.controller;

import java.util.List;

import com.portalmis.serviceimpl.Columns;

public class MisResponse {
	private List<Columns> columns;

	private List<com.portalmis.serviceimpl.Datas> Datas;

	public List<Columns> getColumns() {
		return columns;
	}

	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}

	public List<com.portalmis.serviceimpl.Datas> getDatas() {
		return Datas;
	}

	public void setDatas(List<com.portalmis.serviceimpl.Datas> datas) {
		Datas = datas;
	}

}
