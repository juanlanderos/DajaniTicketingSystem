package com.jpa.dajaniTestDB.service;

import com.jpa.dajaniTestDB.entity.User;
import com.jpa.dajaniTestDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //POST methods
    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return userRepository.saveAll(users);
    }

    //GET methods
    public List<User> getUsers(List<User> users){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    //doesn't make sense to delete users in our case - skip DELETE methods

    //UPDATE + DELETE methods - get input from Luis/Christian on this

    /*
    //come back to this in testing:
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        existingUser.setTickets(user.getTickets()
    }
    */

}
