package com.geeksforgeeks.useraccounts.controller;


import com.ashish.exception.NotFoundException;
import com.ashish.common.models.Account;
import com.geeksforgeeks.useraccounts.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getAccountInfoByUser(@PathVariable UUID userId){
        try {
            Account account = this.accountService.getAccountByUserId(userId);
            return ResponseEntity.ok(account);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
