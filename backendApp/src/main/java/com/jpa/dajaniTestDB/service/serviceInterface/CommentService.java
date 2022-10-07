package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.CommentModel;

import java.util.List;

public interface CommentService {

    CommentModel createComment(CommentModel commentModel, Integer ticketId, Integer userId);

    List<CommentModel> getAllComments();
}
