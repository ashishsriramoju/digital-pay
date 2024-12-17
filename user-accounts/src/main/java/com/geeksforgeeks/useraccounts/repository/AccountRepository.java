package com.geeksforgeeks.useraccounts.repository;


import com.ashish.common.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("select a from Account a where a.user.id = ?1")
    Optional<Account> findByUserId(UUID id);
}
