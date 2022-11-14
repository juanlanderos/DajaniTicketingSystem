package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;


import java.util.List;

public interface TicketService {

    List<TicketEntity> getAllTickets();

    TicketEntity findByTicketId(Integer ticketId);

    TicketEntity findByTicketTitle(String title);

    boolean deleteByTicketId(Integer ticketId);

    boolean removeUserFromTicket(Integer ticketId, Integer userId);

    TicketEntity createNewTicket(Integer userId, TicketEntity TicketEntity);

    void addUserToTicket(Integer userId, Integer ticketId);

    List<UserEntity> getAllUsersByTicketId(Integer ticketId);

    List<CommentEntity> getAllCommentsByTicketId(Integer ticketId);

}
