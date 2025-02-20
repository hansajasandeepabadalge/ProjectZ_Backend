package com.app.simple.services;

import com.app.simple.dto.SignupRequest;

public interface AuthService {
    boolean createCustomer(SignupRequest signupRequest);
}
