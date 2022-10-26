package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel saveUser(UserModel userModel);

    List<UserModel> showAllUsers();

    UserModel getUserById(int id);

    UserModel getUserByEmail(String email);

    UserModel getUserByFirstName(String firstName);

    UserModel getUserByLastName(String lastName);
}
