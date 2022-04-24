package com.jpa.dajaniTestDB.repository;
import java.time.Instant;
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

    //create new comment
    @Test
    public void saveComment(){

        //pk not included here since it's auto-incremented
        Comment comment = Comment.builder()
                .content("pls help")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        commentRepository.save(comment);
    }

}