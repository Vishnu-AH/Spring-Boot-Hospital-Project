package com.ty.springBoot_Hospital.Exception;

import lombok.Data;

@Data
public class MedItemsNotFoundException extends RuntimeException{
	private String message;
	public MedItemsNotFoundException() {
		
	}
	public MedItemsNotFoundException(String message) {
		this.message = message;
	}
}
