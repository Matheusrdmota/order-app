package com.example.demo.user.usecases.interactors;

import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;

public interface FindUserInteractor {
    UserRegisterResponseDTO findUserByEmail(String email);
}
