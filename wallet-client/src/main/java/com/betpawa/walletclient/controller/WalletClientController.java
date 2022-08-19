package com.betpawa.walletclient.controller;

import com.betpawa.walletclient.service.WalletClientService;
import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletClientController {

    WalletClientService service;


    @PostMapping("deposit/{account}")
    public Map<Descriptors.FieldDescriptor, Object> reserveMoney(@PathVariable("account") Long account) {
        return service.receiveMoney(account);
    }

    @PostMapping("withdraw/{account}")
    public Map<Descriptors.FieldDescriptor, Object> addMoney(@PathVariable("account") Long account) {
        return service.addMoney(account);
    }

    @GetMapping("balance/{account}")
    public List<Map<Descriptors.FieldDescriptor, Object>> balance(@PathVariable("account") Long account) throws InterruptedException {
        return service.balance(account);
    }

}
