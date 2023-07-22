package com.portalmis.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallbackVendor {
	
	private long totalCount;
	private String cpid;
	private String date;
	private String vendor;
	private String shared;
	private String supress;
	

}
