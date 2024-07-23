package com.example.est_spring.weekly_4_quiz.service;

import com.example.est_spring.weekly_4_quiz.entity.Customer;
import com.example.est_spring.weekly_4_quiz.dto.CustomerDto;
import com.example.est_spring.weekly_4_quiz.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerDto.toEntity();
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerDto.fromEntity(savedCustomer);
    }

}
