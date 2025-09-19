package com.sylvester.student.controller;


import com.sylvester.student.Authentication.AuthenticationRequest;
import com.sylvester.student.Authentication.AuthenticationResponse;
import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;
import com.sylvester.student.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterStudentResponse> register(@RequestBody @Valid RegisterStudentRequest registerStudentRequest) {
        return ResponseEntity.ok(authenticationService.registerStudent(registerStudentRequest));
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest request) {

        return authenticationService.authenticate(request);
    }
}
