package com.betpawa.betting.controller;

import com.betpawa.betting.model.Bet;
import com.betpawa.betting.model.Game;
import com.betpawa.betting.service.BettingService;
import com.betpawa.betting.service.impl.BettingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BettingController.class)
class BettingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BettingServiceImpl service;

    @Test
    void balance() {

    }

    @Test
    void shouldGetListOfBets() {
        List<Bet> list = new ArrayList<>();
        list.add(new Bet());
        given(service.getAll()).willReturn(list);
        try {
            this.mockMvc.perform(get("/api/betting/all")).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldGetBetById() throws Exception {
        Bet bet = Bet.builder()
                .id(1l)
                .gameId(new Game())
                .userId(1l)
                .status("WIN")
                .build();
        given(service.findBetById(bet.getId())).willReturn(Optional.of(bet).get());

        this.mockMvc.perform(get("/api/betting/{account}", bet.getId())).andExpect(status().isOk());
    }
}