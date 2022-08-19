package com.betpawa.betting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Game {

    @Id
    private Long id;

    private String city;

    private LocalDateTime date;
    @JoinColumn(name = "team_one")
    private String teamOne;

    @JoinColumn(name = "odds_team_one")
    private int oodsTeamOne;

    @JoinColumn(name = "team_two")
    private String teamTwo;

    @JoinColumn(name = "odds_team_two")
    private int oodsTeamTwo;


}
