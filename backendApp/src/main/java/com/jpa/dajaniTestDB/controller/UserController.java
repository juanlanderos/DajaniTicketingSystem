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

    @Operation(summary = "Adds a user to DB")
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
        return userService.getAllUsers();
    }

    @Operation(summary = "Fetches a user from DB based on their ID")
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
}
