package com.ty.springBoot_Hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.AddressNotFoundException;
import com.ty.springBoot_Hospital.Exception.BranchNotFoundException;
import com.ty.springBoot_Hospital.Exception.HospitalNotFoundException;
import com.ty.springBoot_Hospital.dao.AddressDao;
import com.ty.springBoot_Hospital.dao.BranchDao;
import com.ty.springBoot_Hospital.dao.HospitalDao;
import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;
	@Autowired
	private HospitalDao hospitalDao;
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<Response<Branch>> saveBranch(Branch branch, int hid, int aid) {
		if (hospitalDao.getHospitalById(hid) != null) {
			if (addressDao.getAddressById(aid) == null) {
				throw new AddressNotFoundException("Address ID is invalid");
			} else {
				Response<Branch> response = new Response<>();
				response.setMessage("successfully saved");
				response.setHttpStatus(HttpStatus.CREATED.value());
				response.setData(branchDao.saveBranch(hid, aid, branch));
				return new ResponseEntity<Response<Branch>>(response, HttpStatus.CREATED);
			}
		} else {
			throw new HospitalNotFoundException("Hospital ID is invalid");
		}
	}

	public ResponseEntity<Response<Branch>> updateBranch(Branch branch, int bid, int aid, int hid) {
		Response<Branch> response = new Response<>();
		if (branchDao.updateBranch(branch, bid, aid, hid) != null) {
			if (addressDao.getAddressById(aid) == null) {
				throw new AddressNotFoundException("Invalid Address ID");
			} else if (hospitalDao.getHospitalById(hid) == null) {
				throw new HospitalNotFoundException("Invalid Hospital ID");
			} else {
				response.setHttpStatus(HttpStatus.OK.value());
				response.setData(branchDao.updateBranch(branch, bid, aid, hid));
				return new ResponseEntity<Response<Branch>>(response, HttpStatus.OK);
			}
		} else {
			throw new BranchNotFoundException("Invalid Branch ID");
		}
	}

	public ResponseEntity<Response<Branch>> deleteBranch(int id) {
		Response<Branch> response = new Response<>();
		if (branchDao.deleteBranch(id) != null) {
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(branchDao.deleteBranch(id));
			return new ResponseEntity<Response<Branch>>(response, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException("Invalid Branch ID");
		}

	}

	public ResponseEntity<Response<Branch>> getBranchById(int id) {

		if (branchDao.getBranchById(id) != null) {
			Response<Branch> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(branchDao.getBranchById(id));
			return new ResponseEntity<Response<Branch>>(response, HttpStatus.FOUND);
		} else {
			throw new BranchNotFoundException("Invalid Branch ID");
		}

	}

	public ResponseEntity<Response<List<Branch>>> getAllBranchByHospital(int hid) {
		List<Branch> daoBranch = branchDao.getAllBranchByHospital(hid);
		if (daoBranch != null) {
			Response<List<Branch>> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoBranch);
			return new ResponseEntity<Response<List<Branch>>>(response, HttpStatus.FOUND);
		} else {
			throw new BranchNotFoundException();
		}

	}
}
