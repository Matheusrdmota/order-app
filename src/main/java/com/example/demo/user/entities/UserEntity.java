package com.example.demo.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserEntity {
    private String name;
    private String cpf;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Date birthday;
    private String role;

    public boolean hasName(){
        return this.name != "" && this.name != null;
    }

    public boolean hasEmail(){
        return this.username != "" && this.username != null;
    }

    public boolean hasCpf(){
        return this.cpf != "" && this.cpf != null;
    }

    public boolean isPasswordValid(){
        return this.password.length() >= 8;
    }

    public boolean isPhoneValid(){
        return this.phone.length() == 11;
    }

    public boolean validate(){
        return this.hasCpf()
                && this.hasEmail()
                && this.hasName()
                && this.isPasswordValid()
                && this.isPhoneValid();
    }
}
