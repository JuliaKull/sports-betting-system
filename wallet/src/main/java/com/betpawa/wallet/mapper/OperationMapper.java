package com.betpawa.wallet.mapper;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.entity.User;
import com.betpawa.wallet.entity.UserOperation;
import com.betpawa.wallet.rest.response.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper implements WebMapper<Operation, UserOperation>{
    @Override
    public Operation toDTO(UserOperation entity) {
        return Operation.builder()
                .type(entity.getOperation())
                .amount(entity.getAmount())
                .reference(entity.getReference())
                .build();
    }

    @Override
    public UserOperation toEntity(Operation dto) {
        return UserOperation.builder()
                .operation(dto.type())
                .amount(dto.amount())
                .reference(dto.reference())
                .build();
    }
}
