package br.com.andrewsilva.customer.api.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrewsilva.customer.api.entity.Customer;
import br.com.andrewsilva.customer.api.responses.Response;
import br.com.andrewsilva.customer.api.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Response<Customer>> create(HttpServletRequest request, @RequestBody Customer customer,
			BindingResult result) {
		Response<Customer> response = new Response<Customer>();
		try {
			validateCreateCustomer(customer, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Customer customerPersitead = customerService.createOrUpdate(customer);
			response.setData(customerPersitead);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateCustomer(Customer customer, BindingResult result) {
		if (customer.getName() == null) {
			result.addError(new ObjectError("Customer", "Name not informed."));
		}
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Customer>> update(HttpServletRequest request, @RequestBody Customer customer,
			BindingResult result) {
		Response<Customer> response = new Response<Customer>();
		try {
			validateUpdateCustomer(customer, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Customer currentCustomer = customerService.findById(customer.getId()).get();
			currentCustomer.setName(customer.getName());
			currentCustomer.setAddress(customer.getAddress());
			Customer customerPersisted = customerService.createOrUpdate(currentCustomer);
			response.setData(customerPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdateCustomer(Customer customer, BindingResult result) {
		if (customer.getId() == null) {
			result.addError(new ObjectError("Customer", "Id not informed."));
		}
		if (customer.getName() == null) {
			result.addError(new ObjectError("Customer", "Name not informed."));
		}
	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Customer>> findById(@PathVariable("id") String id) {
		Response<Customer> response = new Response<Customer>();
		Optional<Customer> customer = customerService.findById(id);
		if (!customer.isPresent()) {
			response.getErrors().add("Register not foung by id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(customer.get());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		if (!customerService.existsById(id)) {
			response.getErrors().add("Register not foung by id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		customerService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Customer>>> findAll(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Response<Page<Customer>> response = new Response<Page<Customer>>();
		Page<Customer> customers = customerService.findAll(page, count);
		response.setData(customers);
		return ResponseEntity.ok(response);
	}

}
