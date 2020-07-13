package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.repository.admin.mybatis.UserRepository;
import com.kimhong.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User newUser) {
        return userRepository.saveUser(newUser) ? newUser : null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User updateByUserId(User newUser) {
        return userRepository.updateByUserId(newUser)? newUser : null;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }
}