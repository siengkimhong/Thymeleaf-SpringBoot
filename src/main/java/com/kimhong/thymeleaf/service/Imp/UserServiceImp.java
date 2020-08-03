package com.kimhong.thymeleaf.service.Imp;

import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.repository.admin.mybatis.UserRepository;
import com.kimhong.thymeleaf.service.UserService;
import com.kimhong.thymeleaf.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (userRepository.saveUser(newUser)){
            if (userRepository.createUserRole(newUser)){
                return newUser;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll(Paging paging) {
        paging.setTotalCount(userRepository.countUser());
        return userRepository.findAll(paging);
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User updateByUserId(User newUser) {
        if (userRepository.updateByUserId(newUser)){
            if (userRepository.updateUserRole(newUser)){
                return newUser;
            }
        }
        return null;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public List<User> searchUserByKeyword(String keyword, Paging paging) {
        paging.setTotalCount(userRepository.countSearchResult(keyword));
        return userRepository.searchUserByKeyword(keyword, paging);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.selectUserByEmail(email);
    }
}
