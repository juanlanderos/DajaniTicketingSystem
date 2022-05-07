package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //public List<User>

    //Update Admin
    @Modifying
    @Transactional
    @Query(
            value = "update admin =?1 where user_id = ?2",
            nativeQuery = true
    )
    int updateAdmin( Integer user_id );

    //Update Agent
    @Modifying
    @Transactional
    @Query(
            value = "update agent =?1 where user_id = ?2",
            nativeQuery = true
    )
    int updateAgent( Integer user_id );

    //Update Requester
    @Modifying
    @Transactional
    @Query(
            value = "update requester =?1 where user_id = ?2",
            nativeQuery = true
    )
    int updateRequester( Integer user_id );


}
