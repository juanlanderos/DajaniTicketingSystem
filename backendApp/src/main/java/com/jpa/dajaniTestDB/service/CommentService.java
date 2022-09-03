/*
package com.jpa.dajaniTestDB.service;

import com.jpa.dajaniTestDB.entity.Comment;
import com.jpa.dajaniTestDB.service.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //POST methods - doesn't make sense to save a list of comments in our case
    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    //GET methods
    public Comment getCommentById(int id){
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentByUserId(int id){
        return commentRepository.getCommentByUserID(id);
    }
    
    //public List<Comment> getCommentsByUserId(int id){
        //return commentRepository.findByUser(id);
    //}
}
*/
