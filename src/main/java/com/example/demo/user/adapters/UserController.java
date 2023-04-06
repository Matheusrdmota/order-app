package com.example.demo.user.adapters;

import com.example.demo.user.usecases.dtos.UserRegisterRequestDTO;
import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;
import com.example.demo.user.usecases.interactors.FindUserInteractor;
import com.example.demo.user.usecases.interactors.UserRegisterInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRegisterInteractor interactor;
    @Autowired
    private FindUserInteractor findUserInteractor;

    @PostMapping
    public ResponseEntity<UserRegisterResponseDTO> saveUser(@RequestBody UserRegisterRequestDTO user){
        return ResponseEntity.ok(this.interactor.createUser(user));
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserRegisterResponseDTO> findUser(@PathVariable String email){
        return ResponseEntity.ok(this.findUserInteractor.findUserByEmail(email));
    }
}
