package com.geeksforgeeks.useraccounts.repository;

import com.ashish.common.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    @Query("select u from User u where u.name = ?1")
    Optional<User> findByName(String name);

    @Query("select u from User u where u.phone = ?1")
    Optional<User> findByPhone(String phone);

}
