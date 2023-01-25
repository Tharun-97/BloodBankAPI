package com.bloodbank.model;

public class BloodGroup {
	private int id;
	private String bloodgroup;

	public BloodGroup(int id, String bloodgroup) {
		super();
		this.id = id;
		this.bloodgroup = bloodgroup;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

}
