package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>, JpaSpecificationExecutor<Status> {
}