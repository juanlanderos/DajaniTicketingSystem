package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel saveUser(UserModel userModel);

    List<UserModel> showAllUsers();

    UserModel getUserById(int id);

    List<UserModel> getUsersByFirstName(String firstName);

    List<UserModel> getUsersByLastName(String lastName);

    List<UserModel> getUsersByFullName(String firstName, String lastName);

    UserModel getUserByEmail(String email);
}
