package com.ty.springBoot_Hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.AddressNotFoundException;
import com.ty.springBoot_Hospital.dao.AddressDao;
import com.ty.springBoot_Hospital.dto.Address;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<Response<Address>> saveAddress(Address address) {
		Response<Address> response = new Response<Address>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(addressDao.saveAddress(address));

		return new ResponseEntity<Response<Address>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Address>> updateAddress(int id, Address address) {
		Response<Address> response = new Response<Address>();
		if (addressDao.updateAddress(id, address) != null) {
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(addressDao.updateAddress(id, address));
			return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
		} else {
			throw new AddressNotFoundException("Invalid Address ID");
		}
	}

	public ResponseEntity<Response<Address>> deleteAddress(int id) {
		Response<Address> response = new Response<Address>();
		if (addressDao.deleteAddress(id) != null) {
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(addressDao.deleteAddress(id));
			return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
		} else {
			throw new AddressNotFoundException("Invalid Address ID");

		}
	}

	public ResponseEntity<Response<Address>> getAddressById(int id) {
		Response<Address> response = new Response<Address>();
		if (addressDao.getAddressById(id) != null) {
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(addressDao.getAddressById(id));
			return new ResponseEntity<Response<Address>>(response, HttpStatus.FOUND);
		} else {
			throw new AddressNotFoundException("Invalid Address ID");
		}
	}
}
