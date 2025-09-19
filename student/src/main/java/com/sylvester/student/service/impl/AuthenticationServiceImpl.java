package com.sylvester.student.service.impl;

import com.sylvester.student.Authentication.AuthenticationRequest;
import com.sylvester.student.Authentication.AuthenticationResponse;
import com.sylvester.student.config.JwtService;
import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;
import com.sylvester.student.model.entity.Role;
import com.sylvester.student.model.entity.Student;
import com.sylvester.student.repository.StudentRepository;
import com.sylvester.student.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
private final StudentRepository studentRepository;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String identifier = request.getEmail();

        Student student = studentRepository.findByEmail(identifier)
                .orElseThrow(() -> new RuntimeException("User not found with email " + identifier));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        identifier,
                        request.getPassword()
                )
        );

        String jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(jwtToken) // you may want a real refresh token later
                .build();
    }

    @Override
    public RegisterStudentResponse registerStudent(RegisterStudentRequest request) {

        if (studentRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number already exists!");
        }
        if (studentRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email number already exists!");
        }

        Student newStudent = Student.builder()
                .name(request.getName())
                .dob(request.getDob())
                .phone(request.getPhone())
                .email(request.getEmail())
                .department(request.getDepartment())
                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
                .role(Role.USER)
                .build();

        Student savedStudent = studentRepository.save(newStudent);
        return new RegisterStudentResponse("Student Registered Successfully", savedStudent);
    }

}
