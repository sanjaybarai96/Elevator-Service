package com.model;

import org.json.JSONObject;

public class JsonResponse {
	
	public static JSONObject SuccessResponse(String msg) {
		return new JSONObject().put("status", true)
						.put("msg", msg);
	}
	
	public static JSONObject FailureResponse(String msg) {
		return new JSONObject().put("status", false)
				.put("msg", msg);
		
	}
	
	
}
