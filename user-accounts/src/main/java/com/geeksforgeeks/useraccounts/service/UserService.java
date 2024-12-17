package com.geeksforgeeks.useraccounts.service;


import com.ashish.exception.NotFoundException;
import com.ashish.common.models.User;
import com.geeksforgeeks.useraccounts.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final AccountService accountService;

    private final UserRepository userRepository;

    public UserService(AccountService accountService, UserRepository userRepository){
        this.accountService = accountService;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user){
        this.userRepository.save(user);
        this.accountService.addAccount(user);
        log.info("new user and account added");
        return user;
    }

    public User getUserById(UUID id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(()->{
            NotFoundException ex  = new NotFoundException(User.class, "id", id);
            log.info(ex.getMessage());
            return ex;
        });
    }

}
