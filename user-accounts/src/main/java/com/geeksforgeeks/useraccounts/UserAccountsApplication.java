package com.geeksforgeeks.useraccounts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.geeksforgeeks.useraccounts")
@ComponentScan(basePackages = {"com.geeksforgeeks.useraccounts", "com.ashish.transaction"})
@EntityScan(basePackages = "com.ashish.common.models")
@EnableJpaRepositories(basePackages = {"com.ashish.transaction.repository","com.geeksforgeeks.useraccounts.repository"})
public class UserAccountsApplication {
    public static void main(String[] args){
        SpringApplication.run(UserAccountsApplication.class, args);
    }
}
