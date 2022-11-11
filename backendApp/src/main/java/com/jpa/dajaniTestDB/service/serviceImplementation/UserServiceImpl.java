package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.repository.RoleRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    public UserModel saveUser(UserModel userModel) {
        log.info("Saving new user {} to the database", userModel.getUsername());
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userModel, userEntity);
        userRepository.save(userEntity);
        return userModel;
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
    public UserModel getUserByUsername(String username) {
        log.info("Fetching user {} from the database", username);
        UserEntity tempUserEntity = userRepository.findByUsername(username);
        UserModel tempUserModel = new UserModel();
        BeanUtils.copyProperties(tempUserEntity, tempUserModel);
        return tempUserModel;
    }

    @Override
    public List<UserModel> showAllUsers() {
        log.info("Fetching all users");
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserModel> userModels = userEntities
                .stream()
                .map(tempUser -> new UserModel(
                        tempUser.getUserId(),
                        tempUser.getUsername(),
                        tempUser.getEmail(),
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getPassword(),
                        tempUser.getRoles(),
                        tempUser.getCommentEntityList(),
                        tempUser.getTicketEntities()
                ))
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

    @Override
    public UserModel getUserByFirstName(String firstName) {
        UserEntity tempUserEntity = userRepository.findByFirstName(firstName);
        UserModel tempUserModel = new UserModel();
        BeanUtils.copyProperties(tempUserEntity, tempUserModel);
        return tempUserModel;
    }

    @Override
    public UserModel getUserByLastName(String lastName) {
        UserEntity tempUserEntity = userRepository.findByLastName(lastName);
        UserModel tempUserModel = new UserModel();
        BeanUtils.copyProperties(tempUserEntity, tempUserModel);
        return tempUserModel;
    }

    @Override
    public List<TicketModel> getTicketsByUserId(int userId){
        UserEntity userEntity = userRepository.findById(userId).get();
        List<TicketEntity> ticketEntityList = userEntity.getTicketEntities();
        List<TicketModel> ticketModelList = ticketEntityList
                .stream()
                .map(tempTicket -> new TicketModel(
                        tempTicket.getTicketId(),
                        tempTicket.getTitle(),
                        tempTicket.getDescription(),
                        tempTicket.getCreatedAt(),
                        tempTicket.getUpdatedAt(),
                        tempTicket.getCompletedAt(),
                        tempTicket.getStatus(),
                        tempTicket.getCommentEntityList(),
                        tempTicket.getTicketUsers(),
                        tempTicket.getRequesterId(),
                        tempTicket.getAgentId()
                ))
                .collect(Collectors.toList());
        return ticketModelList;

    }
}

