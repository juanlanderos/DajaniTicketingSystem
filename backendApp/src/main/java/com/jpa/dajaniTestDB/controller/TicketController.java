package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
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

    @GetMapping("/tickets")
    public List<TicketEntity> showAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketEntity> showTicketById(@PathVariable int id){
        TicketEntity tempTicketModel;
        tempTicketModel = ticketService.findByTicketId(id);
        return ResponseEntity.ok(tempTicketModel);
    }

    @GetMapping("/ticketUsers/{id}")
    public List<UserEntity> getUsersFromTicket(@PathVariable int id){
        return ticketService.getAllUsersByTicketId(id);
    }

    @GetMapping("/ticket-comments/{id}")
    public List<CommentEntity> getAllCommentsByTicketId(@PathVariable int id){
        return ticketService.getAllCommentsByTicketId(id);
    }

    @PostMapping("/tickets/{userId}")
    public TicketEntity addTicket(@PathVariable Integer userId, @RequestBody TicketEntity ticketModel){
        return ticketService.createNewTicket(userId, ticketModel);
    }

    @PostMapping("/tickets/{userId}/{ticketId}")
    public void addUserToTicket(@PathVariable Integer userId, @PathVariable Integer ticketId){
        ticketService.addUserToTicket(userId, ticketId);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTicketById(@PathVariable Integer id){
        boolean deleted;
        deleted = ticketService.deleteByTicketId(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
