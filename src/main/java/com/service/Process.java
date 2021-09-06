package com.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;
import org.json.JSONObject;

import com.model.CallElevator;
import com.model.JsonResponse;
import com.utils.BtnType;
import com.utils.Constants;
import com.utils.ElevatorState;

public class Process extends Thread{

	private static Process self = null;
	private static Map<String,String> result = new LinkedHashMap<>();
	private static Map<String,Integer> upCallQue = new LinkedHashMap<>();
	private static Map<String,Integer> upCallQueSorted = new LinkedHashMap<>();
	private static Map<String,Integer> dnCallQue = new LinkedHashMap<>();
	private static Map<String,Integer> dnCallQueSorted = new LinkedHashMap<>();
	private static int time = 2000;
	public static Process getInstance() {
		if(self==null) {
			self = new Process();
		}
		return self;
	}

	public String getElevator(CallElevator callElevator) {
		while(true) {
			if(Constants.currFloor==callElevator.passengerFloor) {
				Constants.movingState=ElevatorState.IDLE.getCode();
				Constants.isMovingState = false;
				delayTime(time);
				break;
			}else if(Constants.currFloor>callElevator.passengerFloor){
				Constants.movingState=ElevatorState.DOWN.getCode();
				Constants.isMovingState = true;
				Constants.currFloor--;
				delayTime(time);
			}else {
				Constants.movingState=ElevatorState.UP.getCode();
				Constants.isMovingState = true;
				Constants.currFloor++;
				delayTime(time);
			}
		}
		return JsonResponse.SuccessResponse(BtnType.getBtnType(callElevator.btnType).getDesc()+Constants.currFloor).toString();
	}


	private void delayTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String addingInQueue(CallElevator callElevator) {
		if(Constants.currFloor < callElevator.passengerFloor) {
			upCallQue.put(callElevator.uid+"~"+callElevator.btnType, callElevator.passengerFloor);
			upCallQue.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.forEachOrdered(x -> upCallQueSorted.put(x.getKey(), x.getValue()));
		}
		if(Constants.currFloor > callElevator.passengerFloor) {
			dnCallQue.put(callElevator.uid+"~"+callElevator.btnType, callElevator.passengerFloor);
			dnCallQue.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.forEachOrdered(x -> dnCallQueSorted.put(x.getKey(), x.getValue()));
		}
		if(Constants.currFloor == callElevator.passengerFloor) {
			result.put(callElevator.uid+"~"+callElevator.btnType, JsonResponse.SuccessResponse(BtnType.getBtnType(callElevator.btnType).getDesc()+" "+Constants.currFloor).toString());
		}
		while(true) {
			if(result.containsKey(callElevator.uid+"~"+callElevator.btnType))
				break;
		}
		String msg = result.get(callElevator.uid+"~"+callElevator.btnType);
		result.remove(callElevator.uid+"~"+callElevator.btnType);
		return msg;
	}

	@Override
	public void run() {
		while(true) {
			delayTime(time);
			if(upCallQueSorted.size()>0) {
				Map.Entry<String,Integer> entry = upCallQueSorted.entrySet().iterator().next();
				String key = entry.getKey();
				Integer value = entry.getValue();
				upCallQue.remove(key);
				upCallQueSorted.remove(key);
				result.put(key,getElevator(new CallElevator(value,key.split("~")[1])));
			}else if(dnCallQueSorted.size()>0) {
				Map.Entry<String,Integer> entry = dnCallQueSorted.entrySet().iterator().next();
				String key = entry.getKey();
				Integer value = entry.getValue();
				upCallQue.remove(key);
				dnCallQueSorted.remove(key);
				result.put(key,getElevator(new CallElevator(value,key.split("~")[1])));
			}
		}
	}
}





