package com.betpawa.betting.service.impl;

import com.betpawa.betting.model.Game;
import com.betpawa.betting.repository.GameRepository;
import com.betpawa.betting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findBetById(Long id) {
        return gameRepository.findById(id).get();
    }
}
