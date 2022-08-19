package com.betpawa.wallet.service;

import com.betpawa.wallet.dto.Balance;
import com.betpawa.wallet.dto.MoneyTransferData;
import com.betpawa.wallet.dto.MoneyTransferResult;
import com.betpawa.wallet.rest.response.OperationsResponse;

public interface WalletService {
    MoneyTransferResult creditAccount(MoneyTransferData transferData);

    MoneyTransferResult debitAccount(MoneyTransferData transferData);

    Balance balance(Long accountId);
    Balance createNewAccount(String email, String password);

    Balance findAccount(String email);

    OperationsResponse listOperations(Long id);
}
