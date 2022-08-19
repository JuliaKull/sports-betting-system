package com.betpawa.wallet.mapper;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper implements WebMapper<Balance, User>{
    @Override
    public Balance toDTO(User entity) {
        return Balance.builder()
                .accountId(entity.getId())
                .amount(entity.getBalance())
                .build();
    }

    @Override
    public User toEntity(Balance dto) {
        return User.builder()
                .id(dto.accountId())
                .balance(dto.amount())
                .build();
    }
}
