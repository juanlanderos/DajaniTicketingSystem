package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Test
    public User findAUser(Integer user_id){
        return userRepository.findById(user_id).orElse(null);
    }

    @Test
    public void addUser(){
        User user = User.builder()
                .admin(0)
                .agent(1)
                .requester(0)
                .build();
        userRepository.save(user);
    }
    //Integer user_Id
    @Test
    public void updateAdmin(){
        User user = userRepository.findById(1).orElse(null);
        user.setAdmin(0);
        user.setAgent(1);
        user.setRequester(0);
        userRepository.save(user);
    }
    //Integer user_Id
    @Test
    public void updateAgent(){
        User user = userRepository.findById(1).orElse(null);
        user.setAdmin(0);
        user.setAgent(0);
        user.setRequester(1);
        userRepository.save(user);
    }
    //Integer user_Id
    @Test
    public void updateRequester(){
        User user = userRepository.findById(1).orElse(null);
        user.setAdmin(1);
        user.setAgent(0);
        user.setRequester(0);
        userRepository.save(user);
    }
}