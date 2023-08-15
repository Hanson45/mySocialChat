package com.mychatsocial.usermicroservice.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String name;
    private String lastname;
    private String email;
    private String password;
}
