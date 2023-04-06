package com.example.demo.user.usecases.interactorsImpl;

import com.example.demo.user.entities.UserBuilder;
import com.example.demo.user.entities.UserEntity;
import com.example.demo.user.usecases.dtos.UserRegisterRequestDTO;
import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;
import com.example.demo.user.usecases.gateways.UserGateway;
import com.example.demo.user.usecases.interactors.UserRegisterInteractor;

public class UserRegisterInteractorImpl implements UserRegisterInteractor {
    private UserGateway gateway;

    public UserRegisterInteractorImpl(UserGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public UserRegisterResponseDTO createUser(UserRegisterRequestDTO user) {
        if(this.gateway.findUser(user.getUsername()) != null){
            throw new RuntimeException("Usuário com este email já cadastrado!");
        }

        UserEntity userEntity = UserBuilder.create()
                .name(user.getName())
                .cpf(user.getCpf())
                .phone(user.getPhone())
                .address(user.getAddress())
                .birthday(user.getBirthday())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        if(!userEntity.validate()){
            throw new IllegalArgumentException("Argumentos inválidos!");
        }

        return this.gateway.crateUser(user);
    }
}
