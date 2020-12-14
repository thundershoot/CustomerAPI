package br.com.andrewsilva.customer.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.andrewsilva.customer.api.entity.Customer;
import br.com.andrewsilva.customer.api.entity.User;

@Component
public interface CustomerService {

	Customer createOrUpdate(Customer ticket);

	Optional<Customer> findById(String id);

	void delete(String id);
	
	boolean existsById(String id);

	Page<Customer> findAll(int page, int count);

}
