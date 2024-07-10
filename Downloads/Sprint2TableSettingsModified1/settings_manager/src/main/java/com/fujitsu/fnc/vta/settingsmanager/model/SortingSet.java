package com.fujitsu.fnc.vta.settingsmanager.model;

import java.util.List;

import lombok.Data;

@Data
public class SortingSet {
	
	private String sortName;
	private List<SortList> sortList;
}
