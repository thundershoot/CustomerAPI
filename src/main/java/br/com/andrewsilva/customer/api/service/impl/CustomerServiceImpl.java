package br.com.andrewsilva.customer.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import br.com.andrewsilva.customer.api.entity.Customer;
import br.com.andrewsilva.customer.api.entity.User;
import br.com.andrewsilva.customer.api.repository.CustomerRepository;
import br.com.andrewsilva.customer.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createOrUpdate(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> findById(String id) {
		return this.customerRepository.findById(id);
	}

	@Override
	public void delete(String id) {
		this.customerRepository.deleteById(id);
	}

	@Override
	public Page<Customer> findAll(int page, int count) {
		return this.customerRepository.findAll(PageRequest.of(page, count));
	}

	@Override
	public boolean existsById(String id) {
		return this.customerRepository.existsById(id);
	}

}
