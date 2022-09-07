package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.TicketModel;

import java.util.List;

public interface TicketService {

    TicketModel createTicket(TicketModel ticketModel);

    List<TicketModel> getAllTickets();

    TicketModel updateTicket(Integer id, TicketModel ticketModel);

    TicketModel findByTicketId(Integer ticketId);

    boolean deleteByTicketId(Integer ticketId);
}
