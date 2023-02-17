package com.ty.springBoot_Hospital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.BranchNotFoundException;
import com.ty.springBoot_Hospital.Exception.EncounterNotFoundException;
import com.ty.springBoot_Hospital.Exception.PersonNotFoundException;
import com.ty.springBoot_Hospital.dao.BranchDao;
import com.ty.springBoot_Hospital.dao.EncounterDao;
import com.ty.springBoot_Hospital.dao.PersonDao;
import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class EncounterService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private EncounterDao encounterDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<Response<Encounter>> saveEncounter(Encounter encounter, int personId, int branchId) {
		if (personDao.getPersonById(personId) != null) {
			if (branchDao.getBranchById(branchId) == null) {
				throw new BranchNotFoundException("Branch ID is invalid");
			} else {
				Response<Encounter> response = new Response<>();
				Branch branch = branchDao.getBranchById(branchId);
				ArrayList<Branch> branches = new ArrayList<>();
				branches.add(branch);
				encounter.setBranches(branches);
				encounter.setPerson(personDao.getPersonById(personId));
				response.setMessage("Successfully saved");
				response.setData(encounterDao.saveEncounter(encounter));
				response.setHttpStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
			}
		} else {
			throw new PersonNotFoundException("Person ID is invalid");
		}

	}

	public ResponseEntity<Response<Encounter>> updateEncounter(Encounter encounter, int encounterId, int branchId) {
		if (encounterDao.getEncounterById(encounterId) != null) {
			if (branchDao.getBranchById(branchId) == null) {
				throw new BranchNotFoundException("Invalid branch ID");
			} else {
				Encounter dbEncounter = encounterDao.getEncounterById(encounterId);
				Branch branch = branchDao.getBranchById(branchId);
				List<Branch> branches = dbEncounter.getBranches();
				branches.add(branch);
				encounter.setBranches(branches);
				encounter.setPerson(dbEncounter.getPerson());
				Response<Encounter> response = new Response<>();
				response.setMessage("Successfully updated");
				response.setData(encounterDao.updateEncounter(encounterId, encounter));
				response.setHttpStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
			}
		} else {
			throw new EncounterNotFoundException("Invalid encounter ID");
		}
	}

	public ResponseEntity<Response<Encounter>> deleteEncounter(int encounterId) {
		Response<Encounter> response = new Response<Encounter>();
		if (encounterDao.getEncounterById(encounterId) != null) {
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(encounterDao.deleteEncounter(encounterId));
			return new ResponseEntity<Response<Encounter>>(response, HttpStatus.OK);
		} else {
			throw new EncounterNotFoundException("Invalid Encounter ID");

		}
	}

	public ResponseEntity<Response<Encounter>> getEncounterById(int encounterId) {
		if (encounterDao.getEncounterById(encounterId) != null) {
			Response<Encounter> response = new Response<Encounter>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(encounterDao.getEncounterById(encounterId));

			return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
		} else {
			throw new EncounterNotFoundException("Invalid Encounter ID");
		}
	}
}
