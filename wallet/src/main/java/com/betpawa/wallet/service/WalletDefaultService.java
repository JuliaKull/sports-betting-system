package com.betpawa.wallet.service;

import com.betpawa.wallet.dto.*;
import com.betpawa.wallet.entity.User;
import com.betpawa.wallet.entity.UserOperation;
import com.betpawa.wallet.mapper.BalanceMapper;
import com.betpawa.wallet.mapper.OperationMapper;
import com.betpawa.wallet.mapper.WebMapper;
import com.betpawa.wallet.repository.UserOperationRepository;
import com.betpawa.wallet.repository.UserRepository;
import com.betpawa.wallet.rest.response.Operation;
import com.betpawa.wallet.rest.response.OperationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.betpawa.wallet.dto.TransferStatusType.NOT_ENOUGH_MONEY;
import static com.betpawa.wallet.dto.TransferStatusType.OK;

@Service
public class WalletDefaultService implements WalletService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOperationRepository userOperationRepository;

    @Autowired
    private BalanceMapper webMapper;

    @Autowired
    private OperationMapper operationMapper;


    @Override
    public MoneyTransferResult creditAccount(MoneyTransferData transferData) {
        final var accountId = userRepository.findById(transferData.accountId()).get();
        Balance dtoUser = webMapper.toDTO(accountId);
        var statusType = OK;
        var message = "Money has been sent to the bank account";
        if (accountId.getBalance().compareTo(transferData.amount()) == 1) {
            accountId.getBalance().subtract(transferData.amount());
        } else {
            statusType = NOT_ENOUGH_MONEY;
            message = "Operation failed";
        }
        return new MoneyTransferResult(dtoUser.accountId(), statusType, message);
    }

    @Override
    public MoneyTransferResult debitAccount(MoneyTransferData transferData) {
        final var accountId = userRepository.findById(transferData.accountId()).get();
        Balance dtoUser = webMapper.toDTO(accountId);
        dtoUser.amount().add(transferData.amount());
        return new MoneyTransferResult(dtoUser.accountId(), OK, "Money has been sent to the account ");
    }

    @Override
    public Balance balance(Long accountId) {
       User user = userRepository.findById(accountId).get();
        Balance dtoUser = webMapper.toDTO(user);
        return dtoUser;

    }

    @Override
    public Balance createNewAccount(String email, String password) {
        var user = User.builder()
                .email(email)
                .password(password)
                .balance(BigDecimal.valueOf(0))
                .build();
        Balance userBalance = new Balance(user.getId(), user.getBalance());
        final User entity = webMapper.toEntity(userBalance);
        userRepository.save(entity);
        return userBalance;
    }

    @Override
    public Balance findAccount(String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email must be not Null or Empty.");
        }
        User accountFromDb = userRepository.findByEmail(email);
        if (accountFromDb == null) {
            String message = "Account with email = " + email + " does not exist.";
            throw new RuntimeException(message);
        }
        Balance dtoUser = webMapper.toDTO(accountFromDb);;
        return dtoUser;
    }

    @Override
    public OperationsResponse listOperations(Long id) {
        List<UserOperation> allById = userOperationRepository.findAllById(id);
        List<Operation> listOfOperation = operationMapper.toDtos(allById);
        return new OperationsResponse(listOfOperation, true);
    }
}
