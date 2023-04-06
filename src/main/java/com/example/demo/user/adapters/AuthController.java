package com.example.demo.user.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
public class AuthController {

    private BCryptPasswordEncoder encoder;

    @Autowired
    AuthenticationManager authManager;

    public AuthController(BCryptPasswordEncoder encoder){
        this.encoder = encoder;
    }
}
