package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.model.CommentModel;
import com.jpa.dajaniTestDB.service.repository.CommentRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        CommentEntity tempCommentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentModel, tempCommentEntity);
        return null;
    }

    @Override
    public List<CommentModel> getAllComments() {
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentModel> commentModels = commentEntityList
                .stream()
                .map(com -> new CommentModel(
                        com.getCommentId(),
                        com.getContent(),
                        com.getCreatedAt(),
                        com.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return commentModels;
    }

    @Override
    public CommentModel updateCommentStatus(Integer id, CommentModel commentModel) {
        CommentEntity tempCommentEntity
                = commentRepository.findById(id).get();
        tempCommentEntity.setCommentId(tempCommentEntity.getCommentId());
        tempCommentEntity.setContent(tempCommentEntity.getContent());
        tempCommentEntity.setCreatedAt(tempCommentEntity.getUpdatedAt());
        tempCommentEntity.setUpdatedAt(tempCommentEntity.getCreatedAt());

        commentRepository.save(tempCommentEntity);
        return commentModel;
    }


    //not really sure if this is useful
    @Override
    public CommentModel findByCommentId(Integer commentId) {
        CommentEntity commentEntity =
                commentRepository.findById(commentId).get();
        CommentModel commentModel = new CommentModel();
        BeanUtils.copyProperties(commentEntity, commentModel);
        return commentModel;
    }
}
