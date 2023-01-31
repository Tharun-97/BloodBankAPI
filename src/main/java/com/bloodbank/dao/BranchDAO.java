package com.bloodbank.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bloodbank.model.Branch;
import com.bloodbank.util.ConnectionPooling;

@Configuration
public class BranchDAO {

	@Autowired
	ConnectionPooling connectionPooling;

	public List<Branch> getBranchByRegionID(int id) {

		String branchlist = "exec getbranchbyregionid @region_id=?";
		PreparedStatement getbranch;
		List<Branch> branch = new ArrayList<Branch>();
		try {
			getbranch = connectionPooling.connect().prepareStatement(branchlist);
			getbranch.setInt(1, id);
			ResultSet rs = getbranch.executeQuery();

			while (rs.next()) {
				branch.add(new Branch(rs.getInt("branch_id"), rs.getString("branch")));
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return branch;
	}

	public String selectBranchLocation(int id) {

		String query = "exec selectbranchlocation @branch_id=?";
		PreparedStatement selectbranch;
		String branch = "";
		try {
			selectbranch = connectionPooling.connect().prepareStatement(query);
			selectbranch.setInt(1, id);
			ResultSet data = selectbranch.executeQuery();

			while (data.next()) {
				branch = data.getString("branch");
			}
			System.out.println("Data retrived successfully  " + branch);
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return branch;
	}

}
