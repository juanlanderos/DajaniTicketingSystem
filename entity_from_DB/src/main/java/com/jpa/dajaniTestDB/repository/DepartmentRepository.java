package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}