package com.betpawa.betting.controller;

import com.betpawa.betting.model.Bet;
import com.betpawa.betting.service.impl.BettingServiceImpl;
import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/betting")
@AllArgsConstructor
public class BettingController {

    BettingServiceImpl service;


    @GetMapping("balance/{account}")
    public List<Map<Descriptors.FieldDescriptor,Object>> balance (@PathVariable("account") Long account) throws InterruptedException {
       return service.balance(account);
}

    @GetMapping("/all")
    public List<Bet> all(){
        return service.getAll();
    }

    @GetMapping("/{account}")
    public Bet findById (@PathVariable("account") Long account){
        return service.findBetById(account);
    }


}
