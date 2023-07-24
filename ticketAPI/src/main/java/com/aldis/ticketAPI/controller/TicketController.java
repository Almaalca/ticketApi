package com.aldis.ticketAPI.controller;


import com.aldis.ticketAPI.dto.TicketDTO;
import com.aldis.ticketAPI.dto.TicketResponse;
import com.aldis.ticketAPI.entity.State;
import com.aldis.ticketAPI.entity.Ticket;
import com.aldis.ticketAPI.repository.StateRepository;
import com.aldis.ticketAPI.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private StateRepository stateRepository;

    @QueryMapping
    Iterable<Ticket> tickets(){
        return ticketRepository.findAll();
    }

    @MutationMapping
    Ticket createTicket(@Argument TicketDTO ticket){
        State state = stateRepository.findById(ticket.getStateId()).orElseThrow(
                ()-> new RuntimeException(String.format("Estado con %s no encontrado",ticket.getStateId()))
        );

        Ticket ticketSave = new Ticket();
        ticketSave.setCreationDate(new Date());
        ticketSave.setUpdateDate(new Date());
        ticketSave.setUser(ticket.getUser());
        ticketSave.setState(state);
        ticketSave = ticketRepository.save(ticketSave);
        return ticketSave;
    }

    @MutationMapping
    Ticket updateTicket(@Argument TicketDTO ticket){
        Ticket ticketUpdate = ticketRepository.findById(ticket.getId()).orElseThrow(
                ()-> new RuntimeException(String.format("ticket con %s no encontrado",ticket.getId()))
        );
        State state = stateRepository.findById(ticket.getStateId()).orElseThrow(
                ()-> new RuntimeException(String.format("Estado con %s no encontrado",ticket.getStateId()))
        );
        ticketUpdate.setUpdateDate(new Date());
        ticketUpdate.setUser(ticket.getUser());
        ticketUpdate.setState(state);
        ticketUpdate = ticketRepository.save(ticketUpdate);
        return ticketUpdate;
    }

    @MutationMapping
    String removeTicket(@Argument Long id){
        Ticket ticketDelete = ticketRepository.findById(id).orElseThrow(
                ()-> new RuntimeException(String.format("ticket con %s no encontrado",id))
        );
        ticketRepository.delete(ticketDelete);
        return "Eliminado";
    }
    @QueryMapping
    TicketResponse ticketsFilter(@Argument int page,@Argument  int size){
        Page<Ticket> pageItem = ticketRepository.findAll(PageRequest.of(page, size));
        TicketResponse response = new TicketResponse();
        response.setItems(pageItem.getContent());
        System.out.println("ID: "+pageItem.getContent().get(0).getId());
        response.setTotal(pageItem.getTotalElements());
        return response;
    }
}
