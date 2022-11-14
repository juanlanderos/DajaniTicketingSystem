package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserEntity UserEntity);

    RoleEntity saveRole(RoleEntity role);

    void addRoleToUser(String username, String roleName);

    UserEntity getUserByUsername(String username);

    List<UserEntity> showAllUsers();

    UserEntity getUserById(int id);

    UserEntity getUserByEmail(String email);

    UserEntity getUserByFirstName(String firstName);

    UserEntity getUserByLastName(String lastName);

    List<TicketEntity> getTicketsByUserId(int userId);

    void updateResetPasswordToken(String token, String email);

    void updatePassword(UserEntity UserEntity, String newPassword);

    UserEntity getByResetPasswordToken(String token);
}
