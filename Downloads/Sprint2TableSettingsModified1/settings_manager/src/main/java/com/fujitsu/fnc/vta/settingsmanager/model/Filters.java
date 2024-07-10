package com.fujitsu.fnc.vta.settingsmanager.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Filters {
	
	public Filters() {
	}
	
	public Filters(String filterName, String userId, String serviceName,Boolean save, Boolean makeMyDefault,List<String> columnNames,
			SortingSet sortingOrder, FilterSet filter,String tableSettings) {
		 this.filterName = filterName;
	        this.userId = userId;
	        this.serviceName = serviceName;
	        this.columnNames = columnNames;
	        this.sortingOrder = sortingOrder;
	        this.filter = filter;
	        this.save = save;
	        this.makeMyDefault = makeMyDefault;
	        this.tableSettings = tableSettings ;
	}
	@Id
	private String id; 
	private String userId;
	private String serviceName;
	private String tableSettings;
	private Boolean save;
	private Boolean makeMyDefault;
	private String filterName;
	private List<String> columnNames;
	private SortingSet sortingOrder;
	private FilterSet filter;
	private String timeStamp;
	private int pageSize; 


}

