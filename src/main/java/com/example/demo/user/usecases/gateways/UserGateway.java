package com.example.demo.user.usecases.gateways;

import com.example.demo.user.usecases.dtos.UserRegisterRequestDTO;
import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;

public interface UserGateway {
    UserRegisterResponseDTO crateUser(UserRegisterRequestDTO user);
    UserRegisterResponseDTO findUser(String email);
}
