package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void saveTicket(){

        //Not sure if we need this or not, did this from video but adapted to ours - Daniel

        Instant tempTime = Instant.now();

        Ticket ticket = Ticket.builder()
                .createdAt(tempTime)//not sure what an instant is to note here
               .updatedAt(tempTime)//not sure what an instant is to note here
               .completedAt(null)//not sure what an instant is to note here -Daniel
                .statusId("In Progress")
                //.assigneeId(1)
                //.requesterId("2")
                .build();

        ticketRepository.save(ticket);

    }

    @Test
    public void printAllTicket(){

        List<Ticket> ticketList = ticketRepository.findAll();

        System.out.println("ticketList = " + ticketList);

    }

    @Test
    public void printTicketByAsignee(){

        List<Ticket> tickets = ticketRepository.findByAssigneeId(Integer.parseInt("1"));

        System.out.println("tickets = " + tickets);

    }

    @Test
    public void printTicketByRequester(){

        List<Ticket> tickets = ticketRepository.findByRequesterId(Integer.parseInt("2"));

        System.out.println("tickets = " + tickets);

    }

    @Test
    public void printTicketById(){

        List<Ticket> tickets = ticketRepository.findByTicketId(Integer.parseInt("0"));

        System.out.println("tickets = " + tickets);

    }


    @Test
    public void updateTicketStatusTest(){
        ticketRepository.updateTicketStatus(
                "Completed",
                3
        );
    }

    @Test
    public void updateCompletedAtTest(){
        ticketRepository.updateCompletedAt(
                Instant.now(),
                2
        );

    }

    @Test
    public void updateUpdatedAtTest(){
        ticketRepository.updateUpdatedAt(
                Instant.now(),
                2
        );

    }

    @Test
    public void deleteTicketByIdTest(){
        ticketRepository.deleteTicketById(
                3
        );
    }

}