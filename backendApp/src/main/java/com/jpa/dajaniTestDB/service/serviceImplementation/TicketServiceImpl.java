package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

/*    @Override
    public TicketEntity createTicket(TicketEntity tempTicketEntity){
        TicketEntity tempTicketEntity = new TicketEntity();

        BeanUtils.copyProperties(tempTicketEntity, tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketEntity;
    }*/

    @Override
    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();
        return ticketEntities;
    }

    @Override
    public TicketEntity findByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        TicketEntity TicketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticketEntity, TicketEntity);
        return TicketEntity;
    }

    @Override
    public TicketEntity findByTicketTitle(String title) {
        TicketEntity ticketEntity = ticketRepository.findByTitle(title);
        TicketEntity TicketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticketEntity, TicketEntity);
        return TicketEntity;
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
    public TicketEntity createNewTicket(Integer userId, TicketEntity tempTicketEntity) {
        //TicketEntity tempTicketEntity = new TicketEntity();
        UserEntity tempUserEntity = userRepository.findById(userId).get();
        //BeanUtils.copyProperties(tempTicketEntity, tempTicketEntity);
        tempTicketEntity.getTicketUsers().add(tempUserEntity);
        tempTicketEntity.setRequesterId(userId);
        tempUserEntity.addTicket(tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketEntity;
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
    public List<UserEntity> getAllUsersByTicketId(Integer ticketId) {
        TicketEntity tempTicketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntityList = tempTicketEntity.getTicketUsers();
        return userEntityList;
    }

    @Override
    public List<CommentEntity> getAllCommentsByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        return ticketEntity.getCommentEntityList();
    }
}
