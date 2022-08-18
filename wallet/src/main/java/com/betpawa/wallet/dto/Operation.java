package com.betpawa.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Operation {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String operation;
    private BigDecimal amount;
    private String reference;
}