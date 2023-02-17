package com.ty.springBoot_Hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.EncounterNotFoundException;
import com.ty.springBoot_Hospital.Exception.MedOrderNotFoundException;
import com.ty.springBoot_Hospital.dao.EncounterDao;
import com.ty.springBoot_Hospital.dao.MedOrdersDao;
import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class MedOrdersService {
	@Autowired
	private MedOrdersDao medOrdersDao;
	@Autowired
	private EncounterDao encounterDao;

	public ResponseEntity<Response<MedOrders>> saveMedOrders(MedOrders medOrders, int encounterId) {
		if(encounterDao.getEncounterById(encounterId)!=null) {
			Encounter encounter = encounterDao.getEncounterById(encounterId);
			Response<MedOrders> response = new Response<>();
			medOrders.setEncounter(encounter);
			response.setMessage("Successfully saved");
			response.setData(medOrdersDao.saveMedOrders(medOrders));
			response.setHttpStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
		}
		else {
			throw new MedOrderNotFoundException("Invalid MedOrder ID");
		}
	}

	public ResponseEntity<Response<MedOrders>> updateMedOrders(MedOrders medOrders, int medOrdersId, int encounterId) {
		if(medOrdersDao.getMedOrdersById(medOrdersId)!=null) {
			if(encounterDao.getEncounterById(encounterId)==null) {
				throw new EncounterNotFoundException("Invalid Encounter ID");
			}
			else {
				Encounter encounter = encounterDao.getEncounterById(encounterId);
				Response<MedOrders> response = new Response<>();
				medOrders.setEncounter(encounter);
				response.setMessage("Successfully updated");
				response.setData(medOrdersDao.updateMedOrders(medOrdersId, medOrders));
				response.setHttpStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
			}
		}
		else {
			throw new MedOrderNotFoundException("Invalid MedOrder ID");
		}
	}

	public ResponseEntity<Response<MedOrders>> deleteMedOrder(int medOrderId) {
		if (medOrdersDao.getMedOrdersById(medOrderId) != null) {
			Response<MedOrders> response = new Response<>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(medOrdersDao.deleteMedOrders(medOrderId));
			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.OK);
		} 
		else {
			throw new MedOrderNotFoundException("Invalid MedOrder ID");
		}
	}

	public ResponseEntity<Response<MedOrders>> getMedOrdersById(int medOrderId) {
		
		if (medOrdersDao.getMedOrdersById(medOrderId) != null) {
			Response<MedOrders> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(medOrdersDao.getMedOrdersById(medOrderId));
			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
		} 
		else {
			throw new MedOrderNotFoundException("Invalid MedOrder ID");
		}
	}
}
