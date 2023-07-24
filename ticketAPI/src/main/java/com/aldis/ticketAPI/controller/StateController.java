package com.aldis.ticketAPI.controller;

import com.aldis.ticketAPI.entity.State;
import com.aldis.ticketAPI.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @QueryMapping
    Iterable<State> states(){
        return stateRepository.findAll();
    }
}
