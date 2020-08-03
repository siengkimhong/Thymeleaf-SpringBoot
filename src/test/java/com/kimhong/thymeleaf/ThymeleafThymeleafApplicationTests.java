package com.kimhong.thymeleaf;

import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.repository.admin.mybatis.UserRepository;
import com.kimhong.thymeleaf.service.Imp.ArticleServiceImp;
import com.kimhong.thymeleaf.service.Imp.UserServiceImp;
import com.kimhong.thymeleaf.utils.Paging;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class ThymeleafThymeleafApplicationTests {

    @Autowired
    private UserServiceImp userService;
    @Autowired
    private ArticleServiceImp articleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void saveUser() {
        User insertUser = new User();
        insertUser.setUserId(UUID.randomUUID().toString());
        insertUser.setFirstName("Test First");
        insertUser.setLastName("Test Second");
        insertUser.setEmail("@gmail.com");
        insertUser.setPassword("123456");
        User user = userService.saveUser(insertUser);
        if (user != null) {
            System.out.println("User is saved");
            System.out.println(user);
        } else {
            System.out.println("User cannot be saved");
        }
    }

    @Test
    void findAll(Paging page) {
        List<User> users = userService.findAll(page);
        for (User user :
                users) {
            System.out.println(user.toString());
        }
    }

    @Test
    void updateByUserId() {
        User user = new User();
        String testId = "d7502d08-2c87-4164-b80b-aa09b6bcc385";
        user.setUserId(testId);
        user.setLastName("Leo");
        user.setFirstName("Leo Leo");
        userService.updateByUserId(user);
    }

    @Test
    void findOne() {
        System.out.println(userService.findOne("e1dcb0b1-7bb1-41d0-9684-59860b0c1ff8"));
    }

    @Test
    void deleteByUserId() {
        String userId = "86ea4138-3978-4e42-9a1e-42a49aba2143";
        userService.deleteByUserId(userId);
    }

    @Test
    void contextLoads() {

    }

    @Test
    void showAllArticle() {
        articleService.findAll();
    }

    @Test
    void selectUserByEmail(){
        User user= userRepository.selectUserByEmail("dara@gmail.com");
        System.out.println(user);
    }

    @Test
    void selectRoleByUserId(){
        System.out.println(userRepository.selectRoleByUserId(2));
    }

    @Test
    void generatePassword(){
        System.out.println(encoder.encode("123"));
    }

}
