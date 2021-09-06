package com.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CallElevator {
	@XmlElement public int passengerFloor;
     public String move;
    @XmlElement public String btnType;
    public String uid = UUID.randomUUID().toString();
    
	public CallElevator() {
	}

	public CallElevator(int passengerFloor, String btnType) {
		this.passengerFloor = passengerFloor;
		this.btnType = btnType;
	}
    
    
    
}
