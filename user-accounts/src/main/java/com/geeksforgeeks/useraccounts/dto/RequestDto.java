package com.geeksforgeeks.useraccounts.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private String phone;

    private String password;


}
