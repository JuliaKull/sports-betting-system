package com.betpawa.betting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Bet {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game gameId;

    @JoinColumn(name = "user_id")
    private Long userId;

    private BigDecimal bet;

    private String status;


}
