package com.geeksforgeeks.useraccounts.security;

import com.geeksforgeeks.useraccounts.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService){
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("fileter 1");
        String header = request.getHeader("Authorization");

        System.out.println("header is " + header);

        if(header == null || !header.contains("Bearer")){
            System.out.println("not valid token");
            filterChain.doFilter(request, response);
            return;
        }
        else{
            String token = header.substring(7);
            String username =  jwtUtil.extractUsername(token);
            System.out.println("username is " + username);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                System.out.println("inside the token" + token +" " + username);
                UserDetails ud = myUserDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(token, ud)) {
                    System.out.println("token is validated");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,ud, ud.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
