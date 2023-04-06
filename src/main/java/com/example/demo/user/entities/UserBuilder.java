package com.example.demo.user.entities;

import java.util.Date;

public class UserBuilder {
    private UserEntity user;

    private UserBuilder(){
        this.user = new UserEntity();
    }

    public static UserBuilder create(){
        return new UserBuilder();
    }

    public UserBuilder name(String name){
        this.user.setName(name);
        return this;
    }

    public UserBuilder password(String password){
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder cpf(String cpf){
        this.user.setCpf(cpf);
        return this;
    }

    public UserBuilder username(String email){
        this.user.setUsername(email);
        return this;
    }

    public UserBuilder address(String address){
        this.user.setAddress(address);
        return this;
    }

    public UserBuilder phone(String phone){
        this.user.setPhone(phone);
        return this;
    }

    public UserBuilder birthday(Date birthday){
        this.user.setBirthday(birthday);
        return this;
    }

    public UserBuilder role(String role){
        this.user.setRole(role);
        return this;
    }

    public UserEntity build(){
        return this.user;
    }

}
