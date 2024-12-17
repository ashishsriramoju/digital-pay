package com.geeksforgeeks.useraccounts.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionDto {

    private UUID senderAccId;

    private UUID receiveAccId;

    private double amount;
}
