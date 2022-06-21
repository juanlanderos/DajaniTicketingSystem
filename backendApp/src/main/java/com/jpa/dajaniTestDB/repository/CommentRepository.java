package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public List<Comment> findCommentsByUser(Integer UserId);

    //added a method to return a single comment based on user id
    public Comment findByUser(Integer UserId);

    public Comment findByCommentId(Integer CommentId);

    public List<Comment> findByTicket(Integer ticketId);

    @Modifying
    @Transactional
    @Query(
            value = "select * from Comment where User.user_id = ?1",
            nativeQuery = true
    )
    public List<Comment> getCommentByUserID(int id);

    //public void updateCommentById(Integer CommentId);

    //public void deleteCommentById(Integer CommentId);
}