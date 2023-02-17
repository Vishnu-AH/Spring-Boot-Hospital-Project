package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class BranchNotFoundException extends RuntimeException{

	private String message;
	public BranchNotFoundException(String message) {
		this.message = message;
	}
	public BranchNotFoundException() {
	}
}
