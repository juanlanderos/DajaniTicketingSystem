package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.TicketService;
import org.h2.engine.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

/*    @Override
    public TicketModel createTicket(TicketModel tempTicketModel){
        TicketEntity tempTicketEntity = new TicketEntity();

        BeanUtils.copyProperties(tempTicketModel, tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketModel;
    }*/

    @Override
    public List<TicketModel> getAllTickets() {
        List<TicketEntity> ticketEntities = ticketRepository.findAll();

        List<TicketModel> ticketModels = ticketEntities
                .stream()
                .map(tempTicket -> new TicketModel(
                        tempTicket.getTicketId(),
                        tempTicket.getCreatedAt(),
                        tempTicket.getUpdatedAt(),
                        tempTicket.getCompletedAt(),
                        tempTicket.getStatusId(),
                        tempTicket.getCommentEntityList(),
                        tempTicket.getTicketUsers()
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
    public boolean deleteByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntityList = ticketEntity.getTicketUsers();

        for( UserEntity i : userEntityList){
            i.removeTicket(ticketEntity);
        }
        ticketRepository.delete(ticketEntity);
        return true;
    }

    @Override
    public TicketModel createNewTicket(Integer userId, TicketModel tempTicketModel) {
        TicketEntity tempTicketEntity = new TicketEntity();
        UserEntity tempUserEntity = userRepository.findById(userId).get();
        BeanUtils.copyProperties(tempTicketModel, tempTicketEntity);
        tempTicketEntity.getTicketUsers().add(tempUserEntity);
        tempUserEntity.addTicket(tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketModel;
    }

    @Override
    public void addUserToTicket(Integer userId, Integer ticketId) {
        TicketEntity tempTicketEntity = ticketRepository.findById(ticketId).get();
        UserEntity tempUserEntity = userRepository.findById(userId).get();
        tempTicketEntity.getTicketUsers().add(tempUserEntity);
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
                        tempUser.getEmail(),
                        tempUser.getTicketEntities()
                ))
                .collect(Collectors.toList());
        return userModels;
    }
}
