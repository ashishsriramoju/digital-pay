package com.geeksforgeeks.useraccounts.service;


import com.ashish.exception.NotFoundException;
import com.ashish.common.models.Transaction;
import com.ashish.common.models.Account;
import com.ashish.common.models.User;
import com.ashish.transaction.service.TransactionService;
import com.geeksforgeeks.useraccounts.dto.TransactionDto;
import com.geeksforgeeks.useraccounts.mapper.TransactionMapper;
import com.geeksforgeeks.useraccounts.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    private TransactionMapper transactionMapper;

    private TransactionService transactionService;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionMapper transactionMapper, TransactionService transactionService){
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;
        this.transactionService = transactionService;
    }

    public Account addAccount(User user){
        Account account = new Account().builder()
                .user(user)
                .build();
        this.accountRepository.save(account);
        log.info("account id {} and user is {}", account.getAccountId(), user.getId());
        return account;
    }

    public Account getAccountById(UUID accountId){
        Optional<Account> account  = this.accountRepository.findById(accountId);
        return account.orElseThrow(()-> new NotFoundException(Account.class, "id", accountId));
    }

    public Account getAccountByUser(User user){
        return this.getAccountByUserId(user.getId());
    }

    public Account getAccountByUserId(UUID id){
        return this.accountRepository.findByUserId(id).orElseThrow(()-> new NotFoundException(Account.class, "user-id", id));
    }

    @Transactional
    public Transaction createTransaction(TransactionDto transactionDto){
        Transaction transaction = this.transactionMapper.mapToTransaction(transactionDto);
        Account senderAccount = transaction.getSenderAccount();
        Account receiverAccount = transaction.getReceiverAccount();
        Transaction transaction1 = this.transactionService.createTransaction(transaction);
        senderAccount.send(receiverAccount, transaction.getAmount());
        this.accountRepository.saveAll(List.of(senderAccount, receiverAccount));
        return transaction1;
    }
}
