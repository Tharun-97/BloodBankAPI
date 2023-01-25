package com.bloodbank.model;

import java.util.List;

public class Branch {
	private int id;
	private String branchLocation;
	private List<BloodGroup> bloodgroups;
 
	public Branch(int id, String branchLocation) {
		super();
		this.id = id;
		this.branchLocation = branchLocation;
	}

	public Branch() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranchLocation() {
		return branchLocation;
	}

	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}

	public List<BloodGroup> getBloodgroups() {
		return bloodgroups;
	}

	public void setBloodgroups(List<BloodGroup> bloodgroups) {
		this.bloodgroups = bloodgroups;
	}

}
