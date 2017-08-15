package com.ak.service;

import com.ak.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {
    void save(User user);

    User findByEmail(String email);

    List<User> findAll();

    void delete(Long id);

    User findOne(Long id);

}