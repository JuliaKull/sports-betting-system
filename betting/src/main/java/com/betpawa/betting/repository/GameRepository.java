package com.betpawa.betting.repository;

import com.betpawa.betting.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

}
