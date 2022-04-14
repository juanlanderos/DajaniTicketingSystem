package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository deptRepo;

    //create a new dept
    @Test
    public void saveDept(){
        Department dept = Department.builder()
                //pk not included here - automatically generated
                .name("IT")
                .build();
        deptRepo.save(dept);
    }



}