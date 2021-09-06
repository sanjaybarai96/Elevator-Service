package com.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.model.CallElevator;
import com.model.JsonResponse;
import com.utils.Constants;

@Path("/")
public class Service {
	static Process process = Process.getInstance();
	//give the current status of a elevator
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStatus() {
		return new JSONObject().put("currFloor", Constants.currFloor).put("movingState", Constants.movingState).toString();
	}
	
	@POST
	@Path("/callElevator")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized String callElevator(CallElevator callElevator) {
		if(!Constants.isAlreadyExecuted) {
			return JsonResponse.FailureResponse("Start process first...").toString();
		}
		return	process.addingInQueue(callElevator);
	}
	
	@GET
	@Path("/startProc")
	@Produces(MediaType.APPLICATION_JSON)
	public String startProc() {
		if(!Constants.isAlreadyExecuted) {
			process.start();
			Constants.isAlreadyExecuted=true;
			return JsonResponse.SuccessResponse("Process started...").toString();
		}
		return JsonResponse.SuccessResponse("Process is already started...").toString();
	}
	
}
