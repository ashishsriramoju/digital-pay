package com.ashish.common.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@With
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @OneToOne
    @JsonIgnore
    private User user;

    @Builder.Default
    private double balance = 0.00;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private boolean kycCompleted = false;

    public void send(Account receiverAccount, double amount){
        this.balance -= amount;
        receiverAccount.balance += amount;
    }

}
