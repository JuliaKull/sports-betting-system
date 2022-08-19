package com.betpawa.betting.service.impl;

import com.betpawa.betting.entity.Bet;
import com.betpawa.betting.repository.BetRepository;
import com.betpawa.betting.service.BettingService;
import com.betpawa.wallet.api.proto.AccountDetailsRequest;
import com.betpawa.wallet.api.proto.BalanceResponse;
import com.betpawa.wallet.api.proto.WalletGrpcServiceGrpc;
import com.google.protobuf.Descriptors;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class BettingServiceImpl implements BettingService {

    @Autowired
    private BetRepository betRepository;

@GrpcClient("grpc-wallet-service")
    WalletGrpcServiceGrpc.WalletGrpcServiceStub asynchronousClient;

    public List<Map<Descriptors.FieldDescriptor,Object>> balance(Long account)  {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AccountDetailsRequest request = AccountDetailsRequest.newBuilder().setAccountId(account).build();
        List<Map<Descriptors.FieldDescriptor,Object>> response = new ArrayList<>();
        asynchronousClient.balance(request, new StreamObserver<BalanceResponse>() {
    @Override
    public void onNext(BalanceResponse value) {
       response.add(value.getAllFields());
    }

    @Override
    public void onError(Throwable t) {
countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
countDownLatch.countDown();
    }
});
        boolean await = false;
        try {
            await = countDownLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return await?response: Collections.emptyList();
    }

    @Override
    public List<Bet> getAll() {
        return betRepository.findAll();
    }

    @Override
    public Bet findBetById(Long id) {
        return betRepository.findById(id).get();
    }

    @Override
    public void acceptBet(Long id, BigDecimal bet) {

    }


}
