package com.betpawa.betting.service;

import com.betpawa.betting.model.Bet;
import com.google.protobuf.Descriptors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BettingService {

    List<Map<Descriptors.FieldDescriptor,Object>> balance(Long account);

    List<Bet> getAll();

    Bet findBetById(Long id);

    void acceptBet(Long id, BigDecimal bet) ;


}
