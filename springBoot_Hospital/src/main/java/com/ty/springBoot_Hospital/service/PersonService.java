package com.ty.springBoot_Hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.PersonNotFoundException;
import com.ty.springBoot_Hospital.dao.PersonDao;
import com.ty.springBoot_Hospital.dto.Person;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;

	public ResponseEntity<Response<Person>> savePerson(Person person) {
		Response<Person> response = new Response<>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(personDao.savePerson(person));
		return new ResponseEntity<Response<Person>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Person>> updatePerson(int id, Person person) {
		Response<Person> response = new Response<Person>();
		if (personDao.updatePerson(id, person) != null) {
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(personDao.updatePerson(id, person));
			return new ResponseEntity<Response<Person>>(response, HttpStatus.OK);
		} else {
			throw new PersonNotFoundException("Invalid Person ID");
		}
	}

	public ResponseEntity<Response<Person>> deletePerson(int id) {
		if (personDao.deletePerson(id) != null) {
			Response<Person> response = new Response<Person>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(personDao.deletePerson(id));
			return new ResponseEntity<Response<Person>>(response, HttpStatus.OK);
		} else {
			throw new PersonNotFoundException("Invalid Person ID");
		}
	}

	public ResponseEntity<Response<Person>> getPersonById(int id) {

		if (personDao.getPersonById(id) != null) {
			Response<Person> response = new Response<Person>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(personDao.getPersonById(id));
			return new ResponseEntity<Response<Person>>(response, HttpStatus.FOUND);
		} else {
			throw new PersonNotFoundException("Invalid Person ID");
		}
	}
}
