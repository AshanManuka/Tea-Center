package com.designCenter.designCenter.dto.authUser;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequest {
    private String userName;
    private String password;
}
