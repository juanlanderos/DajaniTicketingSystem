package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
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

    @Operation(summary = "Fetches all users stored in DB by their firstname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all users from DB with specified firstname",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users/firstname/{firstName}")
    public List<UserModel> getUsersByFirstName(@PathVariable("firstName") String firstName){
        return userService.getUsersByFirstName(firstName);
    }

    @Operation(summary = "Fetches all users by their lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetches all users from DB with specified lastname",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users/lastname/{lastName}")
    public List<UserModel> getUsersByLastName(@PathVariable("lastName") String lastName){
        return userService.getUsersByLastName(lastName);
    }

    @Operation(summary = "Fetches all users by their full name [first + last name]")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetches all users from DB with specified name",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/users/fullName/{firstName}/{lastName}")
    public List<UserModel> getUsersByFullName(@PathVariable("firstName") String firstName,
                                              @PathVariable("lastName") String lastName){
        return userService.getUsersByFullName(firstName, lastName);
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
    public UserModel getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @Operation(summary = "Fetches a user from their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched user from DB based on their ID"),
            //content = {@Content(mediaType = "application/json")}),
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

    @Operation(summary = "Updates a user's info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updates user info based on their ID"),
            //content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable int id, @RequestBody UserModel updatedUser){
        UserModel tempUser = userService.getUserById(id);
        tempUser.setAdmin(updatedUser.getAdmin());
        tempUser.setAgent(updatedUser.getAgent());
        tempUser.setRequester(updatedUser.getRequester());
        UserModel savedUser = userService.saveUser(tempUser);
        return ResponseEntity.ok(savedUser);
    }
}
