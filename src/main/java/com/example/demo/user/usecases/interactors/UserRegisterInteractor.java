package com.example.demo.user.usecases.interactors;

import com.example.demo.user.entities.UserEntity;
import com.example.demo.user.usecases.dtos.UserRegisterRequestDTO;
import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;

public interface UserRegisterInteractor {
    UserRegisterResponseDTO createUser(UserRegisterRequestDTO user);
}
