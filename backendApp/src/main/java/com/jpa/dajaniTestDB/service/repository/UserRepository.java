package com.jpa.dajaniTestDB.service.repository;

import com.jpa.dajaniTestDB.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByEmail(String email);
    public UserEntity findByUsername(String username);
    public Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    public UserEntity findByFirstName(String firstName);
    public UserEntity findByLastName(String lastName);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public UserEntity findByResetPasswordToken(String token);
}
