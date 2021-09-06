package com.utils;

public enum BtnType {

	OUTSIDE_BTN("floorBtn","Door is openning at floor :: "),
	INSIDE_BTN("elevatorBtn","Your destination at floor :: ");
	
	private String code;
	private String desc;
	
	private BtnType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}	
	
	public static BtnType getBtnType(String code) {
		for(BtnType btnType:BtnType.values()) {
			if(code.equals(btnType.code)) return btnType;
		}
		return null;
	}
}
