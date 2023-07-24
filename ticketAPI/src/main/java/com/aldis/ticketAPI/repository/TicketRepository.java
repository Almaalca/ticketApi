package com.aldis.ticketAPI.repository;


import com.aldis.ticketAPI.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository  extends JpaRepository<Ticket,Long> {

}
