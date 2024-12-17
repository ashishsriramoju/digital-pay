package com.geeksforgeeks.useraccounts.mapper;


import com.ashish.common.models.Account;
import com.ashish.common.models.Transaction;
import com.geeksforgeeks.useraccounts.dto.TransactionDto;
import com.geeksforgeeks.useraccounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class TransactionMapper {

    private AccountRepository accountRepository;

    @Autowired
    public TransactionMapper(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Transaction mapToTransaction(TransactionDto transactionDto){
        List<Account> accounts  = this.accountRepository.findAllById(List.of(transactionDto.getReceiveAccId(), transactionDto.getSenderAccId()));
        Account senderAccount = null;
        Account receiverAccount = null;
        for(Account account : accounts){
            if(senderAccount != null && receiverAccount != null){
                break;
            }
            if(account.getAccountId().equals(transactionDto.getSenderAccId())){
                senderAccount = account;
            }
            if(account.getAccountId().equals(transactionDto.getReceiveAccId())){
                receiverAccount = account;
            }
        }
        return Transaction.builder()
                .senderAccount(senderAccount)
                .receiverAccount(receiverAccount)
                .createdAt(Instant.now())
                .amount(transactionDto.getAmount())
                .build();
    }

}
