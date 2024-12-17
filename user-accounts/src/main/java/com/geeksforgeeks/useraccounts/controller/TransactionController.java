package com.geeksforgeeks.useraccounts.controller;


import com.ashish.common.models.Transaction;
import com.geeksforgeeks.useraccounts.dto.TransactionDto;
import com.geeksforgeeks.useraccounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private AccountService accountService;


    @Autowired
    public TransactionController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDto transactionDto){
        Transaction newTransaction = this.accountService.createTransaction(transactionDto);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }


}
