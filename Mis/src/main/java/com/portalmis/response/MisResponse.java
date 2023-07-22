package com.portalmis.response;

import java.util.List;

public class MisResponse {
	private List<Columns> columns;

	private List<com.portalmis.response.Datas> Datas;

	public List<Columns> getColumns() {
		return columns;
	}

	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}

	public List<com.portalmis.response.Datas> getDatas() {
		return Datas;
	}

	public void setDatas(List<com.portalmis.response.Datas> datas) {
		Datas = datas;
	}

}
