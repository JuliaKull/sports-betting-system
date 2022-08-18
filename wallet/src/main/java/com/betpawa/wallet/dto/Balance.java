package com.betpawa.wallet.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

public record Balance(Long accountId, BigDecimal amount) {
}
