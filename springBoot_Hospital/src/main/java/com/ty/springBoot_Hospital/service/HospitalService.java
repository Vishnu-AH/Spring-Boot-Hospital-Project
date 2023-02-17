package com.ty.springBoot_Hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.HospitalNotFoundException;
import com.ty.springBoot_Hospital.dao.HospitalDao;
import com.ty.springBoot_Hospital.dto.Hospital;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class HospitalService {
	@Autowired
	private HospitalDao hospitalDao;

	public ResponseEntity<Response<Hospital>> saveHospital(Hospital hospital) {
		Response<Hospital> response = new Response<Hospital>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(hospitalDao.saveHospital(hospital));
		return new ResponseEntity<Response<Hospital>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Hospital>> updateHospital(int id, Hospital hospital) {
		Response<Hospital> response = new Response<Hospital>();
		if (hospitalDao.updateHospital(id, hospital) != null) {
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(hospitalDao.updateHospital(id, hospital));
			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.OK);
		} else {
			throw new HospitalNotFoundException("Invalid Hospital ID");
		}
	}

	public ResponseEntity<Response<Hospital>> deleteHospital(int id) {
		Response<Hospital> response = new Response<Hospital>();
		if (hospitalDao.deleteHospital(id) != null) {
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(hospitalDao.deleteHospital(id));

			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.OK);
		} else {
			throw new HospitalNotFoundException("Invalid Hospital ID");
		}
	}

	public ResponseEntity<Response<Hospital>> getHospitalById(int id) {
		if (hospitalDao.getHospitalById(id) != null) {
			Response<Hospital> response = new Response<Hospital>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(hospitalDao.getHospitalById(id));
			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.FOUND);
		} else {
			throw new HospitalNotFoundException("Invalid Hospital ID");
		}
	}
}
