package com.customer;

import com.customer.controller.TransactionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CustomerTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerTransactionsApplication.class, args);
	}

}
