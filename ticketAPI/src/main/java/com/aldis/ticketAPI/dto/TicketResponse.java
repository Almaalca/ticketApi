package com.aldis.ticketAPI.dto;

import com.aldis.ticketAPI.entity.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketResponse {
    List<Ticket> items;
    Long total;
}
