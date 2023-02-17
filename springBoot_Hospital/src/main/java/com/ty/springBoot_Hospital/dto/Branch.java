package com.ty.springBoot_Hospital.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name should not be blank")
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotBlank(message = "Manager name should not be blank")
	@NotNull(message = "Manager name should not be empty")
	private String manager;
	private long phone;
	@ManyToOne
	private Hospital hospital;
	@OneToOne
	private Address address;
}
