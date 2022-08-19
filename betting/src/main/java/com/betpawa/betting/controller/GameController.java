package com.betpawa.betting.controller;

import com.betpawa.betting.model.Bet;
import com.betpawa.betting.model.Game;
import com.betpawa.betting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService service;

    @GetMapping("/all")
    public List<Game> all(){
        return service.getAll();
    }

    @GetMapping("/{account}")
    public Game findById (@PathVariable("account") Long account){
        return service.findGameById(account);
    }


}
