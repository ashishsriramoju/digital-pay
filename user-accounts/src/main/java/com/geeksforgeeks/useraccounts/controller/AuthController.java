package com.geeksforgeeks.useraccounts.controller;


import com.geeksforgeeks.useraccounts.dto.RequestDto;
import com.geeksforgeeks.useraccounts.security.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody RequestDto requestDto){

        Authentication auth  =  this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getPhone(), requestDto.getPassword()));

        UserDetails ud = (UserDetails) auth.getPrincipal();


        System.out.println("user details are " + ud.toString());

        String jwttoken = this.jwtUtil.generateToken(ud);

        if(auth.isAuthenticated())
        return ResponseEntity.ok(jwttoken);
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


}
