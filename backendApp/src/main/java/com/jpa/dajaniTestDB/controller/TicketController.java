package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.serviceInterface.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @Operation(summary = "Fetches all ticketEntities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all ticketEntities from DB",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/tickets")
    public List<TicketModel> showAllTickets(){
        return ticketService.getAllTickets();
    }

    @Operation(summary = "Fetches a ticketEntity based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched ticketEntity from DB based on its ID"),
            //content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketModel> showTicketById(@PathVariable int id){
        TicketModel tempTicketModel;
        tempTicketModel = ticketService.findByTicketId(id);
        return ResponseEntity.ok(tempTicketModel);
    }

    @Operation(summary = "Fetches users from a ticket based on its ticketID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched ticketEntity and associated Users from DB based on its ID"),
            //content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @GetMapping("/ticketUsers/{id}")
    public List<UserModel> getUsersFromTicket(@PathVariable int id){
        return ticketService.getAllUsersByTicketId(id);
    }

    @Operation(summary = "Creates a new ticketEntity; saves to DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved a new ticketEntity to DB"),
            //content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @PostMapping("/tickets/{userId}")
    public TicketModel addTicket(@PathVariable Integer userId, @RequestBody TicketModel ticketModel){
        return ticketService.createNewTicket(userId, ticketModel);
    }

    @Operation(summary = "Adds a user to a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved a new user to a ticket"),
            //content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @PostMapping("/tickets/{userId}/{ticketId}")
    public void addUserToTicket(@PathVariable Integer userId, @PathVariable Integer ticketId){
        ticketService.addUserToTicket(userId, ticketId);
    }

    @Operation(summary = "Deletes ticket based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deletes ticket by finding its ID in serviceImpl",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not available",
                    content = @Content)
    })
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTicketById(@PathVariable Integer id){
        boolean deleted;
        deleted = ticketService.deleteByTicketId(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
