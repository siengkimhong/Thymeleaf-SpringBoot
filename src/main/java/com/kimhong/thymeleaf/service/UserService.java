package com.kimhong.thymeleaf.service;


import com.kimhong.thymeleaf.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User newUser);
    List<User> findAll();
    User findOne(String userId);

    User updateByUserId(User newUser);
    void deleteByUserId(String userId);
}
