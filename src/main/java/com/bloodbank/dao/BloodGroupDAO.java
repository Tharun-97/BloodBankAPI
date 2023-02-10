package com.bloodbank.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bloodbank.model.BloodGroup;
import com.bloodbank.model.BloodUnits;
import com.bloodbank.model.Branch;
import com.bloodbank.util.ConnectionPooling;

@Configuration
public class BloodGroupDAO {

	@Autowired
	ConnectionPooling connectionPooling;

	public List<BloodGroup> getBloodGroup() {
		String bloodgroups = "exec selectallbloodgroups";
		PreparedStatement getbloodgroup;
		List<BloodGroup> bloodgroup = new ArrayList<BloodGroup>();
		try {
			getbloodgroup = connectionPooling.connect().prepareStatement(bloodgroups);
			ResultSet rs = getbloodgroup.executeQuery();
			while (rs.next()) {
				bloodgroup.add(new BloodGroup(rs.getInt("bloodgroup_id"), rs.getString("bloodgroup")));
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return bloodgroup;
	}

	public String getBloodUnits(String branch, int bloodgroup) {
		String query = "exec getbloodunits @branch=?,@bloodgroup_id=?";
		PreparedStatement getUnits;
		String bloodunit = "";
		try {
			getUnits = connectionPooling.connect().prepareStatement(query);
			getUnits.setString(1, branch);
			getUnits.setInt(2, bloodgroup);
			
			ResultSet data = getUnits.executeQuery();
			while (data.next()) {
				bloodunit = data.getString("unitsavailable");
			}
			System.out.println("Data retrived successfully  " + bloodunit);
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return bloodunit;
	}

	public List<BloodUnits> getAvailableBloodUnits(int bloodgroupid) {
		String AvailableBloodUnits = "exec getAvailableBloodUnits @bloodgroup_id = ?";
		PreparedStatement getunits;
		List<BloodUnits> bloodunits = new ArrayList<BloodUnits>();
		try {
			getunits = connectionPooling.connect().prepareStatement(AvailableBloodUnits);
			getunits.setInt(1, bloodgroupid);
			ResultSet data = getunits.executeQuery();
			
			while (data.next()) {
				Branch branch = new Branch(data.getInt("branch_id"), data.getString("branch_name"));
				BloodUnits bloodUnit = new BloodUnits();
				bloodUnit.setId(data.getInt("blood_unit_id"));
				bloodUnit.setAvailableUnits(data.getInt("unitsavailable"));
				bloodUnit.setBranch(branch);
				bloodunits.add(bloodUnit);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return bloodunits;
	}

}
