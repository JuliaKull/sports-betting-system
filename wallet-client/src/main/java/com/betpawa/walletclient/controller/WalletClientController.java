package com.betpawa.walletclient.controller;

import com.betpawa.walletclient.service.WalletClientService;
import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletClientController {

    WalletClientService service;

/*  @PostMapping("deposit/{account}")
    public OperationResponse deposit(@PathVariable("account") Long account, DepositRequest request) {
        final MoneyTransferResult moneyTransferResult = service.debitAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }

    @PostMapping("withdraw/{account}")
    public OperationResponse withdraw(@PathVariable("account") Long account, WithdrawRequest request) {
        final MoneyTransferResult moneyTransferResult = service.creditAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }*/

    @GetMapping("balance/{account}")
    public List<Map<Descriptors.FieldDescriptor,Object>> balance (@PathVariable("account") Long account) throws InterruptedException {
       return service.balance(account);
}

}
