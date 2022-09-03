package com.jpa.dajaniTestDB.service.serviceInterface;

import com.jpa.dajaniTestDB.model.CommentModel;

import java.util.List;

public interface CommentService {

    CommentModel createComment(CommentModel commentModel);

    List<CommentModel> getAllComments();

    //call when a new comment is added
    //updates timestamp when a new comment is posted
    CommentModel updateCommentStatus(Integer id, CommentModel commentModel);

    //not sure if this is useful tbh
    CommentModel findByCommentId(Integer commentId);

}
