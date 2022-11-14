package com.jpa.dajaniTestDB.service.ServiceInterface;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserService {

    UserModel saveUser(UserModel userModel);

    RoleEntity saveRole(RoleEntity role);

    void addRoleToUser(String username, String roleName);

    UserModel getUserByUsername(String username);

    List<UserModel> showAllUsers();

    UserModel getUserById(int id);

    UserModel getUserByEmail(String email);

    UserModel getUserByFirstName(String firstName);

    UserModel getUserByLastName(String lastName);

    List<TicketModel> getTicketsByUserId(int userId);

    void updateResetPasswordToken(String token, String email);

    void updatePassword(UserModel userModel, String newPassword);

    UserModel getByResetPasswordToken(String token);
}
