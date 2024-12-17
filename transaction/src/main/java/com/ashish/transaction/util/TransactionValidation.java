package com.ashish.transaction.util;

import com.ashish.common.models.Transaction;

public abstract class TransactionValidation {

    public static boolean isValidTransaction(Transaction transaction){
        boolean validMoney = transaction.getSenderAccount().getBalance() >= transaction.getAmount();
        boolean validKyc = transaction.getSenderAccount().isKycCompleted() && transaction.getReceiverAccount().isKycCompleted();
        return validMoney && validKyc;
    }

}
