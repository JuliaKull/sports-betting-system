package com.betpawa.betting.controller;

import com.betpawa.betting.model.Bet;
import com.betpawa.betting.model.Game;
import com.betpawa.betting.service.impl.BettingServiceImpl;
import com.betpawa.betting.service.impl.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GameServiceImpl service;

    @Test
    void shouldGetListOfGames() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        given(service.getAll()).willReturn(list);
        try {
            this.mockMvc.perform(get("/game/all")).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldGetGameById() throws Exception {
        Game game = Game.builder()
                .id(1l)
                .city("London")
                .date(LocalDateTime.now())
                .teamOne("Team1")
                .oodsTeamOne(2)
                .teamTwo("Team2")
                .oodsTeamOne(3)
                .build();
        given(service.findGameById(game.getId())).willReturn(Optional.of(game).get());

        this.mockMvc.perform(get("/game/{account}", game.getId())).andExpect(status().isOk());
    }
}