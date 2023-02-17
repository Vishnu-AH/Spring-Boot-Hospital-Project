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
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name should not be blank")
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotBlank(message = "Email should not be blank")
	@NotNull(message = "Email should not be empty")
	private String email;
	@NotBlank(message = "GST should not be blank")
	@NotNull(message = "GST should not be empty")
	private String gst;
	
}
