package com.example.est_spring.weekly_4_quiz.service;

import com.example.est_spring.weekly_4_quiz.entity.Customer;
import com.example.est_spring.weekly_4_quiz.dto.CustomerDto;
import com.example.est_spring.weekly_4_quiz.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    // 코드 구조를 잘 모르겠단말여
    @Transactional
    public List<CustomerDto> getAllCustomer(CustomerDto customerDto) {
        return customerRepository.findAll().stream()
                .map(CustomerDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 여기서 Optional 타입으로 래핑해야 Controller에서 ResponseEntity로 반환.
    @Transactional
    public Optional<CustomerDto> getCustomer(Long id) {
        return customerRepository.findById(id)
                .map(CustomerDto::fromEntity);
    }

    @Transactional
    public Optional<CustomerDto> updateCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customerDto.getName());
                    existingCustomer.setAddress(customerDto.getAddress());
                    existingCustomer.setOrders(customerDto.toEntity().getOrders());
                    existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
                    return CustomerDto.fromEntity(existingCustomer);
                });
    }

}
