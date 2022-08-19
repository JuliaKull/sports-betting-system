package com.betpawa.walletclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class WalletClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletClientApplication.class, args);
    }

}
