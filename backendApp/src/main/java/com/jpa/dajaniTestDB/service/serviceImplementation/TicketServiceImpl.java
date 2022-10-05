package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.model.TicketModel;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.repository.TicketRepository;
import com.jpa.dajaniTestDB.service.serviceInterface.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketModel createTicket(TicketModel tempTicketModel){
        TicketEntity tempTicketEntity = new TicketEntity();

        BeanUtils.copyProperties(tempTicketModel, tempTicketEntity);
        ticketRepository.save(tempTicketEntity);
        return tempTicketModel;
    }

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
                        tempTicket.getAssigneeId(),
                        tempTicket.getRequesterId(),
                        tempTicket.getCommentEntityList(),
                        tempTicket.getUsersEntityList()
                ))
                .collect(Collectors.toList());
        return ticketModels;
    }

    @Override
    public TicketModel updateTicket(Integer id, TicketModel ticketModel) {
        TicketEntity ticketEntity = ticketRepository.findById(id).get();
        ticketEntity.setUpdatedAt(ticketEntity.getUpdatedAt());
        return ticketModel;
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
        ticketRepository.delete(ticketEntity);
        return true;
    }

    @Override
    public List<UserModel> getAllUsersByTicketId(Integer ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();
        List<UserEntity> userEntities = ticketEntity.getUsersEntityList();
        List<UserModel> userModels = userEntities
                .stream()
                .map(tempUser -> new UserModel(
                        tempUser.getUserId(),
                        tempUser.getAdmin(),
                        tempUser.getAgent(),
                        tempUser.getRequester(),
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail()))
                .collect(Collectors.toList());
        return userModels;
    }
}
