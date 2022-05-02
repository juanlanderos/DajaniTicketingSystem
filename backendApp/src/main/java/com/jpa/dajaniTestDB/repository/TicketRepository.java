package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    public List<Ticket> findByAssigneeId (Integer assigneeId);

    public List<Ticket> findByRequesterId(Integer requesterId);

    public List<Ticket> findByTicketId(Integer TicketId);


    //Update Ticket Status
    @Modifying
    @Transactional
    @Query(
            value = "update ticket set status_ID =?1 where ticket_Id = ?2",
            nativeQuery = true
    )
    int updateTicketStatus(String status, Integer ticketID);

    //Update Completed At
    @Modifying
    @Transactional
    @Query(
            value = "update ticket set completed_at =?1 where ticket_Id = ?2",
            nativeQuery = true
    )
    int updateCompletedAt(Instant completedAt, Integer ticketID);

    //Update Updated At
    @Modifying
    @Transactional
    @Query(
            value = "update ticket set updated_at =?1 where ticket_Id = ?2",
            nativeQuery = true
    )
    int updateUpdatedAt(Instant updatedAt, Integer ticketID);

    //Delete Ticket by ID
    @Modifying
    @Transactional
    @Query(
            value = "delete from ticket where ticket_Id = ?1",
            nativeQuery = true
    )
    int deleteTicketById(Integer ticketID);

    }
