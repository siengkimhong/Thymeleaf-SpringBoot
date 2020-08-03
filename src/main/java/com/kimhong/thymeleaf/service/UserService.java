package com.kimhong.thymeleaf.service;


import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.utils.Paging;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    User saveUser(User newUser);

    List<User> findAll(Paging paging);

    User findOne(String userId);

    User updateByUserId(User newUser);

    void deleteByUserId(String userId);

    List<User> searchUserByKeyword(String keyword, Paging paging);
}
