package com.geeksforgeeks.useraccounts.security;

import com.ashish.common.models.User;
import com.geeksforgeeks.useraccounts.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService{


    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("usename is {}",  username);
        Optional<User> user = this.userRepository.findByPhone(username);

        log.info("user name is {}", user.get().getName());

        if(user.isEmpty()){
            throw new UsernameNotFoundException(String.format("user %s is not found in database", username));
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getPhone())
                .password(user.get().getPassword())
                .build();
    }
}
