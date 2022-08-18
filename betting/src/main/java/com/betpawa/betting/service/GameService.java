package com.betpawa.betting.service;

import com.betpawa.betting.model.Bet;
import com.betpawa.betting.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAll();

    Game findBetById(Long id);
}
