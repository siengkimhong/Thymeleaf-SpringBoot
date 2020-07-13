package com.kimhong.thymeleaf;

import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.service.Imp.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class ThymeleafThymeleafApplicationTests {

    @Autowired
    private UserServiceImp userService;

    @Test
    void saveUser(){
        User insertUser = new User();
        insertUser.setUserId(UUID.randomUUID().toString());
        insertUser.setFirstName("Test First");
        insertUser.setLastName("Test Second");
        insertUser.setEmail("@gmail.com");
        insertUser.setPassword("123456");
        User user = userService.saveUser(insertUser);
        if (user != null){
            System.out.println("User is saved");
            System.out.println(user);
        }
        else{
            System.out.println("User cannot be saved");
        }
    }

    @Test
    void findAll(){
        List<User> users = userService.findAll();
        for (User user :
                users) {
            System.out.println(user.toString());
        }
    }

    @Test
    void updateByUserId(){
        User user = new User();
        String testId = "d7502d08-2c87-4164-b80b-aa09b6bcc385";
        user.setUserId(testId);
        user.setLastName("Leo");
        user.setFirstName("Leo Leo");
        userService.updateByUserId(user);
    }

    @Test
    void findOne(){
        System.out.println(userService.findOne("e1dcb0b1-7bb1-41d0-9684-59860b0c1ff8"));
    }

    @Test
    void deleteByUserId(){
        String userId = "86ea4138-3978-4e42-9a1e-42a49aba2143";
        userService.deleteByUserId(userId);
    }

    @Test
    void contextLoads() {

    }

}
