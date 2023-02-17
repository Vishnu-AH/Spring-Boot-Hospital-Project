package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class PersonNotFoundException extends RuntimeException{
	
	private String message;
	public PersonNotFoundException() {
		
	}
	public PersonNotFoundException(String message) {
		this.message = message;
	}

}
