package com.betpawa.betting.repository;

import com.betpawa.betting.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

}
