package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.CommentModel;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.ServiceInterface.TicketService;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TicketModel> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();

        List<TicketModel> ticketModels = ticketEntities
                .stream()
                .map(tempTicket -> new TicketModel(
                        tempTicket.getTicketId(),
                        tempTicket.getTitle(),
                        tempTicket.getDescription(),
                        tempTicket.getCreatedAt(),
                        tempTicket.getUpdatedAt(),
                        tempTicket.getCompletedAt(),
                        tempTicket.getStatus(),
                        tempTicket.getCommentEntityList(),
                        tempTicket.getTicketUsers(),
                        tempTicket.getRequesterId(),
                        tempTicket.getAgentId()
                ))
                .collect(Collectors.toList());
        return ticketModels;
    }

    @Override
    public TicketModel findByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        TicketModel ticketModel = new TicketModel();
        BeanUtils.copyProperties(ticketEntity, ticketModel);
        return ticketModel;
    }

    @Override
    public TicketModel findByTicketTitle(String title) {
        TicketEntity ticketEntity = ticketRepository.findByTitle(title);
        TicketModel ticketModel = new TicketModel();
        BeanUtils.copyProperties(ticketEntity, ticketModel);
        return ticketModel;
    }

    @Override
    public boolean deleteByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntityList = ticketEntity.getTicketUsers();
        if(ticketEntity != null){
            for( UserEntity i : userEntityList){
                i.removeTicket(ticketEntity);
            }
            ticketRepository.delete(ticketEntity);
            return true;
        }
        else{
            System.err.println("Ticket does not exist");
            return false;
        }
    }

    //remove the user from the list of users and remove requester/agent id
    @Override
    public boolean removeUserFromTicket(Integer ticketId, Integer userId){
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntityList = ticketEntity.getTicketUsers();
        UserEntity removedUser = userRepository.findById(userId).get();
        if(removedUser != null){
            userEntityList.remove(removedUser);
            removedUser.removeTicket(ticketEntity);
            if(userId == ticketEntity.getRequesterId()){
                ticketEntity.setRequesterId(null);
            } else if (userId == ticketEntity.getAgentId()){
                ticketEntity.setAgentId(null);
            }
            return true;
        }
        else{
            System.err.println("User does not exist");
            return false;
        }
    }

    //a new ticket is made by the person who will be the requester
    @Override
    public TicketModel createNewTicket(Integer userId, TicketModel tempTicketModel) {
        TicketEntity tempTicketEntity = new TicketEntity();
        UserEntity tempUserEntity = userRepository.findById(userId).get();
        BeanUtils.copyProperties(tempTicketModel, tempTicketEntity);
        tempTicketEntity.getTicketUsers().add(tempUserEntity);
        tempTicketEntity.setRequesterId(userId);
        tempUserEntity.addTicket(tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketModel;
    }

    //the people who must be added to a pre-existing ticket are agents
    @Override
    public void addUserToTicket(Integer userId, Integer ticketId) {
        TicketEntity tempTicketEntity = ticketRepository.findById(ticketId).get();
        UserEntity tempUserEntity = userRepository.findById(userId).get();
        tempTicketEntity.getTicketUsers().add(tempUserEntity);
        tempTicketEntity.setAgentId(userId);
        tempUserEntity.addTicket(tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
    }

    @Override
    public List<UserModel> getAllUsersByTicketId(Integer ticketId) {
        TicketEntity tempTicketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntityList = tempTicketEntity.getTicketUsers();

        List<UserModel> userModels = userEntityList
                .stream()
                .map(tempUser -> new UserModel(
                        tempUser.getUserId(),
                        tempUser.getUsername(),
                        tempUser.getEmail(),
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getPassword(),
                        tempUser.getRoles(),
                        tempUser.getCommentEntityList(),
                        tempUser.getTicketEntities(),
                        tempUser.getResetPasswordToken()
                ))
                .collect(Collectors.toList());
        return userModels;
    }

    @Override
    public List<CommentModel> getAllCommentsByTicketId(Integer ticketId) {
        log.info("Made it here");
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        List<CommentEntity> commentEntityList = ticketEntity.getCommentEntityList();
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
        log.info("made it to the end");
        return commentModels;
    }

    @Override
    public TicketModel updateTicketStatus(Integer ticketId, String status) {
        TicketEntity tempTicketEntity = ticketRepository.findById(ticketId).get();
        TicketModel tempTicketModel = new TicketModel();
        tempTicketEntity.setStatus(status);
        BeanUtils.copyProperties(tempTicketEntity, tempTicketModel);
        ticketRepository.save(tempTicketEntity);
        return tempTicketModel;
    }
}
