package com.jpa.dajaniTestDB.controller;

import com.jpa.dajaniTestDB.entity.Comment;
import com.jpa.dajaniTestDB.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //POST methods
    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }

    //GET methods
    /*
    @GetMapping("/comments/{id}")
    public List<Comment> getComment(@PathVariable int id){
        return commentService.getCommentByUserId(id);
    }
    */
}
