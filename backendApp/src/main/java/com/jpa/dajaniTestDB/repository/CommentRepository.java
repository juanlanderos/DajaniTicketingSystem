package com.jpa.dajaniTestDB.repository;

import com.jpa.dajaniTestDB.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public List<Comment> findByUser(Integer UserId);

    public Comment findByCommentId(Integer CommentId);


}