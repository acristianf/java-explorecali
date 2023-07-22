package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserControllerTest {

    UserController userController;
    UserRepository userRepository;

    @Autowired
    UserControllerTest(UserController userController, UserRepository userRepository) {
        this.userController = userController;
        this.userRepository = userRepository;
    }

    @Test
    void signup() {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("test");
        userDto.setFirstname("John");
        userDto.setLastname("Doe");
        long usersCount = userRepository.count();
        userController.signup(userDto);
        long afterUsersCount = userRepository.count();
        Assertions.assertEquals(usersCount + 1, afterUsersCount);
    }
}