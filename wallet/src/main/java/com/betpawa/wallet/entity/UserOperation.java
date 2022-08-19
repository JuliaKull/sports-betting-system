package com.betpawa.wallet.entity;

import com.betpawa.wallet.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "operation")
public class UserOperation {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String operation;
    private BigDecimal amount;
    private String reference;
}