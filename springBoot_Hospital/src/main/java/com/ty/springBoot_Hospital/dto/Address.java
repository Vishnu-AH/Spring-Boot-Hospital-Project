package com.ty.springBoot_Hospital.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "City name should not be blank")
	@NotNull(message = "City name should not be empty")
	private String city;
	@NotBlank(message = "Area name should not be blank")
	@NotNull(message = "Area name should not be empty")
	private String area;
	@NotBlank(message = "Street should not be blank")
	@NotNull(message = "Street should not be empty")
	private String street;
	@NotBlank(message = "State name should not be blank")
	@NotNull(message = "State name should not be empty")
	private String state;
	private int pincode;
}
