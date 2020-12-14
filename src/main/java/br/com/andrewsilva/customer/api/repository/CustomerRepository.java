package br.com.andrewsilva.customer.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.andrewsilva.customer.api.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	Page<Customer> findByNameIgnoreCaseContainingOrderByNameAsc(String title, String status,
			String priority, Pageable pages);
}
