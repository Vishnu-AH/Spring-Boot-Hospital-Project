package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class MedOrderNotFoundException extends RuntimeException{
	
	private String message;
	
	public MedOrderNotFoundException() {
	}

	public MedOrderNotFoundException(String message) {
		this.message = message;
	}

}
