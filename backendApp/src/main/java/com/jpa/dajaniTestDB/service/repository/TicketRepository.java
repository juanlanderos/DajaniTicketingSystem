package com.jpa.dajaniTestDB.service.repository;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

}
