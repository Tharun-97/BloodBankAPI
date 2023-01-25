package com.bloodbank.model;

public class BloodUnits {
	private int id;
	private BloodGroup bloodGroup;
	private int availableUnits;
	private Branch branch;

	
	
	public BloodUnits() {
	}

	public BloodUnits(int availableUnits, Branch branch) {
		this.availableUnits = availableUnits;
		this.branch = branch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getAvailableUnits() {
		return availableUnits;
	}

	public void setAvailableUnits(int availableUnits) {
		this.availableUnits = availableUnits;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}
