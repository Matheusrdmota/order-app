package com.example.demo.user.adapters;

import com.example.demo.user.usecases.dtos.UserRegisterRequestDTO;
import com.example.demo.user.usecases.dtos.UserRegisterResponseDTO;
import com.example.demo.user.usecases.gateways.UserGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserService implements UserGateway, UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserRegisterResponseDTO crateUser(UserRegisterRequestDTO user) {
        UserDataMapper userDataMapper = this.mapper.convertValue(user, UserDataMapper.class);
        userDataMapper.setPassword(encoder.encode(user.getPassword()));

        this.repository.save(userDataMapper);

        return  this.mapper.convertValue(user, UserRegisterResponseDTO.class);
    }

    @Override
    public UserRegisterResponseDTO findUser(String email) {
        UserDataMapper userDataMapper = this.repository.findByUsername(email);

        UserRegisterResponseDTO userRegisterResponseDTO = this.mapper.convertValue(userDataMapper, UserRegisterResponseDTO.class);

        return userRegisterResponseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDataMapper userDataMapper = this.repository.findByUsername(username);
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(userDataMapper.getRole());
        return new User(userDataMapper.getUsername(), userDataMapper.getPassword(), authorityList);
    }
}
