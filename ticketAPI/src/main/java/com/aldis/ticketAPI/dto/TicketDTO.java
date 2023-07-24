package com.aldis.ticketAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
    Long id;
    String user;
    Long stateId;
}
