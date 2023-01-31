package com.bloodbank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.dao.BranchDAO;
import com.bloodbank.model.Branch;
import com.bloodbank.model.Response;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {

	@Autowired
	BranchDAO branch;
	Logger logger = LogManager.getLogger("BloodBank");

	@GetMapping(value = "/listOfBranchByRegionId/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	private Response getBranchByRegionId(@PathVariable("id") int id) throws SQLException, IOException {
		logger.info("List of Branches by RegionID API");
		Response response = new Response();
		
		try {
			List<Branch> branches = branch.getBranchByRegionID(id);

			if (branches != null && branches.size() !=0) {
				logger.info("Branch List Details Found "+branches);
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage("Branch List Details found");
				response.setResponseBody(new JSONObject().put("Branch List: ", branches));
				return response;
			} else {
				logger.error("Branch List Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Branch List Not Found");
				return response;
			}
		} catch (Exception e) {
			logger.fatal("Exception Occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Exception Occured");
			e.printStackTrace();
			return response;
		}

	}
	
	
	@GetMapping(value = "/selectBranchById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response selectBranchById(@PathVariable("id") int id) throws Exception {
		logger.info("SelectBranchByID API");
		Response response = new Response();

		try {
			String selectBranch = branch.selectBranchLocation(id);
			if (branch != null && selectBranch.length() !=0) {
				logger.info("Branch Details Found "+selectBranch);
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage("Branch Details found");
				response.setResponseBody(new JSONObject().put("BranchLocation", selectBranch));
				return response;
			} else {
				logger.error("Branch Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Branch Not Found");
				return response;
			}
		} catch (Exception e) {
			logger.fatal("Exception Occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Exception Occured");
			return response;
		}

	}

}
