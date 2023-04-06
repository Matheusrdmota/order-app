package com.example.demo.user.usecases.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    private String name;
    private String cpf;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Date birthday;
    private String role;
}
