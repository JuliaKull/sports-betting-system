package com.betpawa.wallet.rest.response;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record Operation(String type, BigDecimal amount, String reference) {

}
