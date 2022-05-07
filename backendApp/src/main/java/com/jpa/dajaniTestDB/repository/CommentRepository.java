package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public List<Comment> findByUser(Integer UserId);

    public Comment findByCommentId(Integer CommentId);

    //public void updateCommentById(Integer CommentId);

    //public void deleteCommentById(Integer CommentId);
}