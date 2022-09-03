/*
package com.jpa.dajaniTestDB.service;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    //POST methods
    public TicketEntity saveTicket(TicketEntity ticketEntity){
        return ticketRepository.save(ticketEntity);
    }

    //doesn't make sense to save a LIST of ticketEntities in our case

    //GET methods
    public TicketEntity getTicketById(int id){
        return ticketRepository.findById(id).orElse(null);
    }

    public List<TicketEntity> getTicketsById(int id){
        return ticketRepository.findAll();
    }

    //UPDATE methods - get input before implementing

    //DELETE methods - get input before implementing

}
*/
