package com.ty.springBoot_Hospital.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class MedOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Doctor name should not be blank")
	@NotNull(message = "Doctor name should not be empty")
	private String doctorName;
	@CreationTimestamp
	private Date date;
	@ManyToOne
	private Encounter encounter;
}
