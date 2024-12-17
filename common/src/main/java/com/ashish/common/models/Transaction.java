package com.ashish.common.models;

import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    @ManyToOne
    private Account senderAccount;

    @ManyToOne
    private Account receiverAccount;

    @Builder.Default
    private double amount = 0.0D;

    private boolean wasSuccessful;

    @Builder.Default
    private Instant createdAt = Instant.now();


}
