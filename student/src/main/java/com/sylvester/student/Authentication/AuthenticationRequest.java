package com.sylvester.student.Authentication;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
    public class AuthenticationRequest {
    @NotBlank(message = "password can not be blank")
    private String emailOrPhoneNumber;

    @NotBlank(message = "password can not be blank")
        private String password;


}

