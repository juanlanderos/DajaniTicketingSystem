package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.model.CommentModel;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.ServiceInterface.TicketService;
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
    public List<TicketModel> showAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketModel> showTicketById(@PathVariable int id){
        TicketModel tempTicketModel;
        tempTicketModel = ticketService.findByTicketId(id);
        return ResponseEntity.ok(tempTicketModel);
    }

    @GetMapping("/ticketUsers/{id}")
    public List<UserModel> getUsersFromTicket(@PathVariable int id){
        return ticketService.getAllUsersByTicketId(id);
    }

    @GetMapping("/ticket-comments/{id}")
    public List<CommentModel> getAllCommentsByTicketId(@PathVariable int id){
        return ticketService.getAllCommentsByTicketId(id);
    }

    //users create tickets
    @PostMapping("/tickets/{userId}")
    public TicketModel addTicket(@PathVariable Integer userId, @RequestBody TicketModel ticketModel){
        return ticketService.createNewTicket(userId, ticketModel);
    }

    //agents and admins get added to tickets
    @PostMapping("/tickets/{userId}/{ticketId}")
    public void addUserToTicket(@PathVariable Integer userId, @PathVariable Integer ticketId){
        ticketService.addUserToTicket(userId, ticketId);
    }

    @PostMapping("/tickets/remove/{ticketId}/{userId}")
    public void removeUserFromTicket(@PathVariable Integer ticketId, @PathVariable Integer userId){
        ticketService.removeUserFromTicket(ticketId, userId);
    }

    @PostMapping("/tickets/status/{ticketId}/{status}")
    public void updateTicketStatus(@PathVariable Integer ticketId, @PathVariable String status){
        ticketService.updateTicketStatus(ticketId, status);
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
