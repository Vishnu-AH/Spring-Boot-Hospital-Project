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
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name should not be blank")
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotBlank(message = "Email should not be blank")
	@NotNull(message = "Email should not be empty")
	private String email;
	@NotBlank(message = "Address should not be blank")
	@NotNull(message = "Address should not be empty")
	private String address;
	private long phone;
}