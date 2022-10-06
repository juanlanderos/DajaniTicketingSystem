package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;

import java.util.List;

public interface TicketService {

    List<TicketModel> getAllTickets();

    TicketModel findByTicketId(Integer ticketId);

    boolean deleteByTicketId(Integer ticketId);

    TicketModel createNewTicket(Integer userId, TicketModel ticketModel);

    void addUserToTicket(Integer userId, Integer ticketId);

    List<UserModel> getAllUsersByTicketId(Integer ticketId);
}
