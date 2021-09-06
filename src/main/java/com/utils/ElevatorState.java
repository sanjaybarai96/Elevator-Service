package com.utils;

public enum ElevatorState {

	UP("up","going up"),
	DOWN("down","going down"),
	IDLE("idle","idle");
	
	private String code;
	private String desc;
	
	private ElevatorState(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}	
	public static ElevatorState getState(String code) {
		for(ElevatorState state:ElevatorState.values()) {
			if(code.equals(state.code)) return state;
		}
		return null;
	}
}
