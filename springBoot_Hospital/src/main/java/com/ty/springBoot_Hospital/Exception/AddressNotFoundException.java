package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class AddressNotFoundException extends RuntimeException{
	
	private String message;

	public AddressNotFoundException(String message) {
		this.message = message;
	}

	public AddressNotFoundException() {
		
	}
	
}
