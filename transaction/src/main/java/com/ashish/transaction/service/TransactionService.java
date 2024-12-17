package com.ashish.transaction.service;

import com.ashish.common.models.Transaction;
import com.ashish.transaction.repository.TransactionRepository;
import com.ashish.transaction.util.TransactionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    public Transaction createTransaction(Transaction transaction){
        if(!TransactionValidation.isValidTransaction(transaction)){
            return null;
        }
        this.transactionRepository.save(transaction);
        transaction.setWasSuccessful(true);
        return transaction;
    }


}
