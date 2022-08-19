package com.betpawa.wallet.rest;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.dto.MoneyTransferData;
import com.betpawa.wallet.dto.MoneyTransferResult;
import com.betpawa.wallet.rest.request.DepositRequest;
import com.betpawa.wallet.rest.request.LoginAccountRequest;
import com.betpawa.wallet.rest.request.NewAccountRequest;
import com.betpawa.wallet.rest.request.WithdrawRequest;
import com.betpawa.wallet.rest.response.BalanceResponse;
import com.betpawa.wallet.rest.response.OperationResponse;
import com.betpawa.wallet.rest.response.OperationsResponse;
import com.betpawa.wallet.service.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService service;

    @PostMapping("deposit/{account}")
    public OperationResponse deposit(@PathVariable("account") Long account, DepositRequest request) {
        final MoneyTransferResult moneyTransferResult = service.debitAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }

    @PostMapping("withdraw/{account}")
    public OperationResponse withdraw(@PathVariable("account") Long account, WithdrawRequest request) {
        final MoneyTransferResult moneyTransferResult = service.creditAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }

    @PostMapping("account")
    public BalanceResponse create(NewAccountRequest request) {
        final Balance customer = service.createNewAccount(request.getEmail(),request.getPassword());
        return new BalanceResponse(customer.accountId(), customer.amount());
    }

    @PostMapping("find-account")
    public BalanceResponse findAccount(LoginAccountRequest request) {
        final Balance customer = service.findAccount(request.getEmail());
        return new BalanceResponse(customer.accountId(),customer.amount());
    }

    @GetMapping("balance/{account}")
    public BalanceResponse balance(@PathVariable("account") Long account) {
        final Balance balance = service.balance(account);
        return new BalanceResponse(balance.accountId(), balance.amount());
    }
    @GetMapping("operations/{account}")
    public OperationsResponse listOperations(@PathVariable("account") Long account, @RequestParam(value = "take", defaultValue = "20") int take,
                                             @RequestParam(value = "skip", defaultValue = "0") int skip) {
        return service.listOperations(account);
    }

}
