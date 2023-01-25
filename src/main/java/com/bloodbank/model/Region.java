package com.bloodbank.model;

import java.util.List;

public class Region {
	private int id;
	private String region;
	private List<Branch> branch;

	public Region(int id, String region) {

		this.id = id;
		this.region = region;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

}
