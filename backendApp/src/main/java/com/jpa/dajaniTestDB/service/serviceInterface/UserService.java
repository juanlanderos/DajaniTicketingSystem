package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUserById(int id);
}
