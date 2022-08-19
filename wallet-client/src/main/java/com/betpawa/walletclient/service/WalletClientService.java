package com.betpawa.walletclient.service;

import com.betpawa.wallet.api.proto.*;
import com.google.protobuf.Descriptors;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class WalletClientService {

    @GrpcClient("grpc-wallet-service")
    WalletGrpcServiceGrpc.WalletGrpcServiceStub asynchronousClient;

    @GrpcClient("grpc-wallet-service")
    WalletGrpcServiceGrpc.WalletGrpcServiceBlockingStub synchronousClient;


    public List<Map<Descriptors.FieldDescriptor, Object>> balance(Long account) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AccountDetailsRequest request = AccountDetailsRequest.newBuilder().setAccountId(account).build();
        List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
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
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }


    public Map<Descriptors.FieldDescriptor, Object> receiveMoney(Long account) {
        MoneyTransferRequest request = MoneyTransferRequest.newBuilder().setAccountId(account).build();
        Iterator<MoneyTransferResponse> response = synchronousClient.reserveMoney(request);
        return response.next().getAllFields();
    }

    public Map<Descriptors.FieldDescriptor, Object> addMoney(Long account) {
        MoneyTransferRequest request = MoneyTransferRequest.newBuilder().setAccountId(account).build();
        Iterator<MoneyTransferResponse> response = synchronousClient.addMoney(request);
        return response.next().getAllFields();
    }
}