package com.betpawa.wallet.dto;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Builder
public record Balance(Long accountId, BigDecimal amount) {
}
