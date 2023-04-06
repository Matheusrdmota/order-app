package com.example.demo.user.usecases.interactorsImpl;

import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;
import com.example.demo.user.usecases.gateways.UserGateway;
import com.example.demo.user.usecases.interactors.FindUserInteractor;

import java.io.IOException;

public class FindUserInteractorImpl implements FindUserInteractor {
    public UserGateway gateway;

    public FindUserInteractorImpl(UserGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public UserRegisterResponseDTO findUserByEmail(String email) {
        UserRegisterResponseDTO userRegisterResponseDTO = this.gateway.findUser(email);

        if(userRegisterResponseDTO == null){
           throw new RuntimeException("Usuário não encontrado!");
        }

        return userRegisterResponseDTO;
    }
}
