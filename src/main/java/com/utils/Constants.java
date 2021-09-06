package com.utils;

public class Constants {

	public static final int maxFloor = 20;
	public static final int minFloor = 0;
	public static int currFloor = 0;
	public static int destFloor = 0;
	public static String movingState = ElevatorState.IDLE.getCode();
	public static boolean isMovingState = false;
	public static boolean isReqCompl = true;
	public static boolean isAlreadyExecuted = false;
	
}
