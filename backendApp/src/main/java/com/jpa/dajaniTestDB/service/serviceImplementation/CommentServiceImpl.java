package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.CommentModel;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.service.repository.CommentRepository;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TicketRepository ticketRepository,
                              UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentModel createComment(CommentModel commentModel, Integer ticketId, Integer userId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        UserEntity userEntity = userRepository.findById(userId).get();
        CommentEntity tempCommentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentModel, tempCommentEntity);
        tempCommentEntity.setTicketEntity(ticketEntity);
        tempCommentEntity.setUserEntity(userEntity);
        commentRepository.save(tempCommentEntity);
        return commentModel;
    }

    @Override
    public List<CommentModel> getAllComments() {
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentModel> commentModels = commentEntityList
                .stream()
                .map(com -> new CommentModel(
                        com.getCommentId(),
                        com.getTicketEntity(),
                        com.getUserEntity(),
                        com.getContent(),
                        com.getCreatedAt(),
                        com.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return commentModels;
    }
}
