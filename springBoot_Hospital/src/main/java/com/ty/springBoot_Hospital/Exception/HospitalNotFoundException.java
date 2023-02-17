package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class HospitalNotFoundException extends RuntimeException{
	private String message;

	public HospitalNotFoundException(String message) {
		this.message = message;
	}
	public HospitalNotFoundException() {
	}
}
