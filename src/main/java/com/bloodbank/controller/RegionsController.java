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

import com.bloodbank.dao.RegionDAO;
import com.bloodbank.model.Region;
import com.bloodbank.model.Response;

@RestController
@RequestMapping(value = "/Regions")
public class RegionsController {

	@Autowired
	RegionDAO region;
	Logger logger = LogManager.getLogger("BloodBank");

	@GetMapping("/ListOfRegions")
	public Response getRegion() {
		logger.info("List of Regions API");
		Response response = new Response();
		try {
			List<Region> regions = region.getRegion();
			if (regions != null) {
				logger.info("Region List Details Found"+regions);
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage("Region List Details found");
				response.setResponseBody(new JSONObject().put("Region List", regions));
				return response;
			} else {
				logger.error("Region List Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Region List Not Found");
				return response;
			}
		} catch (Exception e) {
			logger.fatal("Exception Occured : " + e.getLocalizedMessage());
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Exception Occured");
			return response;
		}
	}

	@GetMapping(value = "/selectRegionById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Response selectRegionById(@PathVariable("id") int id) throws Exception {
		logger.info("SelectRegionByID API");
		Response response = new Response();

		try {

			String selectRegion = region.selectRegion(id);
			if (region != null) {
				logger.info("Region Details Found"+selectRegion);
				response.setHttpStatus(HttpStatus.FOUND);
				response.setMessage("Region Details found");
				response.setResponseBody(new JSONObject().put("Region", selectRegion));
				return response;
			} else {
				logger.error("Region Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				response.setMessage("Region Not Found");
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
