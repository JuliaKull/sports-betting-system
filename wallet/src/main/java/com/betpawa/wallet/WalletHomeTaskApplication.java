package com.betpawa.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.betpawa.wallet.*")
@EnableJpaRepositories("com.betpawa.wallet")
public class WalletHomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletHomeTaskApplication.class, args);
	}

}
