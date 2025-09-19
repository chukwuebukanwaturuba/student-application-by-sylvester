package com.sylvester.student.Authentication;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotBlank(message = "password can not be blank")
    private String password;
}

