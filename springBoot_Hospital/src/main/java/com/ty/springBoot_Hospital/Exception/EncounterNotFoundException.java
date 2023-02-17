package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class EncounterNotFoundException extends RuntimeException{
	
	private String message;

	public EncounterNotFoundException(String message) {
		this.message = message;
	}
	public EncounterNotFoundException() {
	}
}
