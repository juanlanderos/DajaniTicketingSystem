package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.service.repository.RoleRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    @Override
    public UserEntity saveUser(UserEntity UserEntity) {
        log.info("Saving new user {} to the database", UserEntity.getUsername());
        UserEntity.setPassword(passwordEncoder.encode(UserEntity.getPassword()));
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(UserEntity, userEntity);
        userRepository.save(userEntity);
        return UserEntity;
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        log.info("Saving new role {} to the database", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} to the database", roleName, username);
        UserEntity userEntity = userRepository.findByUsername(username);
        RoleEntity roleEntity = roleRepository.findByRoleName(roleName);
        userEntity.getRoles().add(roleEntity);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        log.info("Fetching user {} from the database", username);
        UserEntity tempUserEntity = userRepository.findByUsername(username);
        return tempUserEntity;
    }

    @Override
    public List<UserEntity> showAllUsers() {
        log.info("Fetching all users");
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities;
    }

    @Override
    public UserEntity getUserById(int id) {
        UserEntity tempUserEntity = userRepository.findById(id).get();
        return tempUserEntity;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity tempUserEntity = userRepository.findByEmail(email);
        //UserEntity tempUserEntity = new UserEntity();
        BeanUtils.copyProperties(tempUserEntity, tempUserEntity);
        return tempUserEntity;
    }

    @Override
    public UserEntity getUserByFirstName(String firstName) {
        UserEntity tempUserEntity = userRepository.findByFirstName(firstName);
        //UserEntity tempUserEntity = new UserEntity();
        BeanUtils.copyProperties(tempUserEntity, tempUserEntity);
        return tempUserEntity;
        //
    }

    @Override
    public UserEntity getUserByLastName(String lastName) {
        UserEntity tempUserEntity = userRepository.findByLastName(lastName);
        //UserEntity tempUserEntity = new UserEntity();
        BeanUtils.copyProperties(tempUserEntity, tempUserEntity);
        return tempUserEntity;
    }

    @Override
    public List<TicketEntity> getTicketsByUserId(int userId){
        UserEntity userEntity = userRepository.findById(userId).get();
        List<TicketEntity> ticketEntityList = userEntity.getTicketEntities();
        return ticketEntityList;

    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        UserEntity tempUserEntity = userRepository.findByEmail(email);
        //UserEntity tempUserEntity = new UserEntity();

        //find the user - if they exist, update their resetPasswordToken and save
        if(tempUserEntity != null){
            tempUserEntity.setResetPasswordToken(token);
            BeanUtils.copyProperties(tempUserEntity, tempUserEntity);
            userRepository.save(tempUserEntity);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public void updatePassword(UserEntity UserEntity, String newPassword) {
        UserEntity tempUserEntity = new UserEntity();
        //encode newPassword and set it as the updated user's password
        String encodedPassword = passwordEncoder.encode(newPassword);
        UserEntity.setPassword(encodedPassword);
        UserEntity.setResetPasswordToken(null);
        BeanUtils.copyProperties(UserEntity, tempUserEntity);
        userRepository.save(tempUserEntity);
    }

    @Override
    public UserEntity getByResetPasswordToken(String token) {
        UserEntity tempUserEntity = userRepository.findByResetPasswordToken(token);
        //UserEntity tempUserEntity = new UserEntity();
        BeanUtils.copyProperties(tempUserEntity, tempUserEntity);
        return tempUserEntity;
    }
}

