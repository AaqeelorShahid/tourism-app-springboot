package com.tour.webapp.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tour.webapp.model.Customers;
import com.tour.webapp.services.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customer")
	public List<Customers> list() {
		
		return service.getListCustomers();
	}
	
	@PostMapping("/customer")
	public String add(@RequestBody Customers addCustomer) {
	    service.save(addCustomer);
	    return "done";
	}
	
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> update(@RequestBody Customers updateCustomer, @PathVariable Integer id) {
	    try {
	         service.getCustomer(id);
	        service.save(updateCustomer);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable Integer id) {
	    service.delete(id);
	}
	
	
	
	
	

}
