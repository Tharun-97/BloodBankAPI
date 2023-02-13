package com.bloodbank.controller;

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

import com.bloodbank.dao.BloodGroupDAO;
import com.bloodbank.model.BloodGroup;
import com.bloodbank.model.BloodUnits;
import com.bloodbank.model.Response;

@RestController
@RequestMapping(value = "/bloodGroups")
public class BloodGroupController {

	@Autowired
	BloodGroupDAO bloodgroup;

	Logger logger = LogManager.getLogger("BloodBank");

	@GetMapping("/listOfBloodGroups")
	public Response getBloodGroups() {

		logger.info("List of BloodGroup API");
		Response response = new Response();

		try {
			List<BloodGroup> bloodGroup = bloodgroup.getBloodGroup();
			if (bloodGroup != null && bloodGroup.size() != 0) {
				logger.info("BloodGroup List Details Found " + bloodGroup.toString());
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage("BloodGroup List Details found");
				response.setResponseBody(new JSONObject().put("ResponseList", bloodGroup));
				return response;
			} else {
				logger.error("BloodGroup List Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("BloodGroup List Not Found");
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

	@GetMapping(value = "/getAvailableUnits/{branch}/{bloodgroupid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response getBloodUnits(@PathVariable("branch") String branch,@PathVariable("bloodgroupid") String input2) {

		logger.info("Get Available Units of BloodGroup API");
		Response response = new Response();

		try {
			
			int bloodgroupid = Integer.parseInt(input2);
			String bloodUnits = bloodgroup.getBloodUnits(branch, bloodgroupid);
			if (bloodUnits != null && bloodUnits.length() != 0) {
				logger.info("Available Units of BloodGroup Details Found " + bloodUnits.toString());
				response.setHttpStatus(HttpStatus.OK);
				response.setMessage("Available Units of BloodGroup Details found");
				response.setResponseBody(new JSONObject().put("Response", bloodUnits));
				return response;
			} else {
				logger.error("Available Units of BloodGroup Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Available Units of BloodGroup Not Found");
				return response;
			}
		} catch (NumberFormatException e) {
			logger.fatal("Number format exception occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("String Input not accepted");
			return response;
		} catch (Exception e) {
			logger.fatal("Exception Occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Exception Occured");
			return response;
		}

	}

	@GetMapping(value = "/listOfBranchAvailableUnits/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response getAvailableBloodUnits(@PathVariable("id") String input) {

		logger.info("List of Available Units in All Branches API");
		Response response = new Response();

		try {
			int id = Integer.parseInt(input);
			List<BloodUnits> availableBloodUnits = bloodgroup.getAvailableBloodUnits(id);

			if (availableBloodUnits != null && availableBloodUnits.size() != 0) {
				logger.info(" Available Units in All Branches Details Found " + availableBloodUnits.toString());
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage(" Available Units in All Branches Details found");
				response.setResponseBody(
						new JSONObject().put("ResponseList", availableBloodUnits));
				return response;
			} else {
				logger.error(" Available Units in All Branches Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage(" Available Units in All Branches Not Found");
				return response;
			}
		} catch (NumberFormatException e) {
			logger.fatal("Number format exception occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("String Input not accepted");
			return response;
		} catch (Exception e) {
			logger.fatal("Exception Occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Exception Occured");
			return response;
		}
	}

}
