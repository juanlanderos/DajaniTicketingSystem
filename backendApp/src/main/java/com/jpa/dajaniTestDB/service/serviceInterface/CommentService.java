package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    CommentEntity createComment(CommentEntity commentModel, Integer ticketId, Integer userId);

    List<CommentEntity> getAllComments();
}
