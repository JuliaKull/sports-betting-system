package com.betpawa.betting.service;

import com.betpawa.betting.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAll();

    Game findGameById(Long id);
}
