package com.jpa.dajaniTestDB.service;

import com.jpa.dajaniTestDB.entity.Ticket;
import com.jpa.dajaniTestDB.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    //POST methods
    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    //doesn't make sense to save a LIST of tickets in our case

    //GET methods
    public Ticket getTicketById(int id){
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getTicketsById(int id){
        return ticketRepository.findAll();
    }

    //UPDATE methods - get input before implementing

    //DELETE methods - get input before implementing

}
