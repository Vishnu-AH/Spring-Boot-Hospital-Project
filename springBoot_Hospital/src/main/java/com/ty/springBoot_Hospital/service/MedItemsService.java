package com.ty.springBoot_Hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.MedItemsNotFoundException;
import com.ty.springBoot_Hospital.Exception.MedOrderNotFoundException;
import com.ty.springBoot_Hospital.dao.MedItemsDao;
import com.ty.springBoot_Hospital.dao.MedOrdersDao;
import com.ty.springBoot_Hospital.dto.MedItems;
import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class MedItemsService {
	@Autowired
	private MedOrdersDao medOrdersDao;
	@Autowired
	private MedItemsDao medItemsDao;

	public ResponseEntity<Response<MedItems>> saveMedItems(MedItems medItems, int medOrderId) {
		if(medOrdersDao.getMedOrdersById(medOrderId)!=null) {
			MedOrders medOrders = medOrdersDao.getMedOrdersById(medOrderId);
			Response<MedItems> response = new Response<>();
			medItems.setMedOrders(medOrders);
			response.setMessage("Successfully saved");
			response.setData(medItemsDao.saveMedItems(medItems));
			response.setHttpStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
		}
		else {
			throw new MedOrderNotFoundException("Invalid MedOrder ID");
		}
	}

	public ResponseEntity<Response<MedItems>> updateMedItems(MedItems medItems, int medItemId, int medOrderId) {
		if(medItemsDao.getMedItemById(medItemId)!=null) {
			if(medOrdersDao.getMedOrdersById(medOrderId)==null) {
				throw new MedOrderNotFoundException("Invalid MedOrder ID");
			}
			else {
				MedOrders medOrders = medOrdersDao.getMedOrdersById(medOrderId);
				Response<MedItems> response = new Response<>();
				medItems.setMedOrders(medOrders);
				response.setMessage("Successfully updated");
				response.setData(medItemsDao.updateMedItems(medItemId, medItems));
				response.setHttpStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
			}
		}
		else {
			throw new MedItemsNotFoundException("Invalid MedItem ID");
		}
	}

	public ResponseEntity<Response<MedItems>> deleteMedItems(int medItemId) {
		if (medItemsDao.getMedItemById(medItemId) != null) {
			Response<MedItems> response = new Response<>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(medItemsDao.deleteMedItem(medItemId));
			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.OK);
		} 
		else {
			throw new MedItemsNotFoundException("Invalid MedItem ID");
		}
	}

	public ResponseEntity<Response<MedItems>> getMedItemById(int medItemId) {
		if (medItemsDao.getMedItemById(medItemId) != null) {
			Response<MedItems> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(medItemsDao.getMedItemById(medItemId));
			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
		} 
		else {
			throw new MedItemsNotFoundException("Invalid MedItem ID");
		}
	}
}
