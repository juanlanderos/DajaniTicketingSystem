package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/username")
    public String currentUserName(Model model, Principal principal) {
        return principal.getName();
    }

    @Operation(summary = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "New user created and stored",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @PostMapping("/users")
    public UserModel createUser(@RequestBody UserModel tempUserModel){
        tempUserModel.setPassword(bCryptPasswordEncoder()
                .encode(tempUserModel.getPassword()));
        return userService.saveUser(tempUserModel);
    }

    @Operation(summary = "Fetches all users stored in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all users from DB",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.showAllUsers();
    }

    @Operation(summary = "Fetches users by their email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetches all users from DB with specified email address",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users/email/{email}")
    public UserModel getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @Operation(summary = "Fetches a user from their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched user from DB based on their ID",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable int id){
        UserModel tempUserModel = null;
        tempUserModel = userService.getUserById(id);
        return ResponseEntity.ok(tempUserModel);
    }
}
