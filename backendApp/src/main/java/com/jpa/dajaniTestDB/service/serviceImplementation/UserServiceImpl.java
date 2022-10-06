package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);
        userRepository.save(userEntity);
        return userModel;
    }

    @Override
    public List<UserModel> showAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserModel> userModels = userEntities
                .stream()
                .map(tempUser -> new UserModel(
                        tempUser.getUserId(),
                        tempUser.getEmail(),
                        tempUser.getTicketEntities())
                        )
                .collect(Collectors.toList());
        return userModels;
    }

    @Override
    public UserModel getUserById(int id) {
        UserEntity tempUserEntity = userRepository.findById(id).get();
        UserModel tempUserModel = new UserModel();
        BeanUtils.copyProperties(tempUserEntity, tempUserModel);
        return tempUserModel;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        UserEntity tempUserEntity = userRepository.findByEmail(email);
        UserModel tempUserModel = new UserModel();
        BeanUtils.copyProperties(tempUserEntity, tempUserModel);
        return tempUserModel;
    }
}

