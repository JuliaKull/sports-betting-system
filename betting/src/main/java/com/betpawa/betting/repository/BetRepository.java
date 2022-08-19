package com.betpawa.betting.repository;

import com.betpawa.betting.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet,Long> {

}
