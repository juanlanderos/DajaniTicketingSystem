package com.jpa.dajaniTestDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private Integer commentId;
    private String content;
    private String createdAt;
    private String updatedAt;
}
