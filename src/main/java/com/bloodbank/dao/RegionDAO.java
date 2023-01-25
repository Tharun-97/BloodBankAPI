package com.bloodbank.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bloodbank.model.Region;
import com.bloodbank.util.ConnectionPooling;

@Configuration
public class RegionDAO {
	@Autowired
	ConnectionPooling connectionPooling;

	public List<Region> getRegion() {
		String regionlist = "exec selectallregions";
		PreparedStatement getregion;
		List<Region> region = new ArrayList<Region>();
		try {
			getregion = connectionPooling.connect().prepareStatement(regionlist);
			ResultSet rs = getregion.executeQuery();

			while (rs.next()) {
				region.add(new Region(rs.getInt("region_id"), rs.getString("region")));
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return region;
	}

	public String selectRegion(int id) {
		String query = "exec selectregionbyid @region_id=?";
		PreparedStatement selectregion;
		String region = "";
		try {
			selectregion = connectionPooling.connect().prepareStatement(query);
			selectregion.setInt(1, id);
			ResultSet data = selectregion.executeQuery();

			while (data.next()) {
				region = data.getString("region");
			}
			System.out.println("Data retrived successfully  " + region);
		} catch (SQLException e) {
			System.err.println("SQL Exception Occoured" + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return region;
	}

}
