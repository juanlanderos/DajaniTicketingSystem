/*
package com.jpa.dajaniTestDB.service;

import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //POST methods
    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public List<UserEntity> saveUsers(List<UserEntity> userEntities){
        return userRepository.saveAll(userEntities);
    }

    //GET methods
    public List<UserEntity> getUsers(List<UserEntity> userEntities){
        return userRepository.findAll();
    }

    public UserEntity getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    //doesn't make sense to delete users in our case - skip DELETE methods

    //UPDATE + DELETE methods - get input from Luis/Christian on this

    */
/*
    //come back to this in testing:
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity existingUser = userRepository.findById(userEntity.getUserId()).orElse(null);
        existingUser.setTicketEntities(userEntity.getTicketEntities()
    }
    *//*


}
*/
