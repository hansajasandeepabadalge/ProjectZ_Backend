package com.app.simple.services;

import com.app.simple.dto.SignupRequest;
import com.app.simple.entities.Customer;
import com.app.simple.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceimpl implements AuthService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceimpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createCustomer(SignupRequest signupRequest) {
        // Check if customer  already exist
        if (customerRepository.existsByEmail(signupRequest. getEmail())) {
            return false;
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest, customer);

        // hash the password
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        customerRepository.save(customer);
        return true;
    }
}
