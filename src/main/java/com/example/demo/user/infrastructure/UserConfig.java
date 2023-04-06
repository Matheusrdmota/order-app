package com.example.demo.user.infrastructure;

import com.example.demo.user.adapters.JpaUserService;
import com.example.demo.user.usecases.interactorsImpl.FindUserInteractorImpl;
import com.example.demo.user.usecases.interactorsImpl.UserRegisterInteractorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Autowired
    private JpaUserService gateway;

    @Bean
    public UserRegisterInteractorImpl userRegisterInteractor(){
        return new UserRegisterInteractorImpl(gateway);
    }

    @Bean
    public FindUserInteractorImpl findUserInteractor(){
        return new FindUserInteractorImpl(gateway);
    }
}
