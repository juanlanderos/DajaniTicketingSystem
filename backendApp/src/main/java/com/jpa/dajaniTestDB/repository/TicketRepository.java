package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    public List<Ticket> findByAssigneeId (Integer assigneeId);

    public List<Ticket> findByRequesterId(Integer requesterId);

    public List<Ticket> findByTicketId(Integer TicketId);

}