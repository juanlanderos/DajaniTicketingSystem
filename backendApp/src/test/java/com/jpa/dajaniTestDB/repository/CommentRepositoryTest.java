package com.jpa.dajaniTestDB.repository;
import java.time.Instant;
import java.util.List;

import com.jpa.dajaniTestDB.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveComment(){


        Comment comment = Comment.builder()
                .ticket(null)
                .content("Need help")
                .user(null)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        commentRepository.save(comment);
    }

    @Test
    public void printAllComment() {
        List<Comment> commentList = commentRepository.findAll();

        System.out.println("commentList = " + commentList);
    }

    @Test
    public void printCommentByUser(){

        List<Comment> comments = commentRepository.findByUser(Integer.parseInt("1"));

        System.out.println("comments = " + comments);

    }


    @Test
    public void printCommentById(){

        Comment comment = commentRepository.findByCommentId(Integer.parseInt("2"));

        System.out.println("comments = " + comment);

    }


}