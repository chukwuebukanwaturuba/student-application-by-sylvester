package com.sylvester.student.service.impl;

import com.sylvester.student.Authentication.AuthenticationRequest;
import com.sylvester.student.config.JwtService;
import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.request.UpdateStudentRequest;
import com.sylvester.student.Authentication.AuthenticationResponse;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;
import com.sylvester.student.model.entity.Role;
import com.sylvester.student.model.entity.Student;
import com.sylvester.student.repository.StudentRepository;
import com.sylvester.student.config.JwtService;;
import com.sylvester.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterStudentResponse registerStudent(RegisterStudentRequest request) {
        // Check if phone already exists
        if (studentRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number already exists!");
        }

        Student newStudent = Student.builder()
                .name(request.getName())
                .dob(request.getDob())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
                .role(Role.USER)
                .build();

        Student savedStudent = studentRepository.save(newStudent);
        return new RegisterStudentResponse("Student Registered Successfully", savedStudent);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found"));
    }

    @Override
    public Student updateStudent(Long id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found"));

        if (request.getName() != null) student.setName(request.getName());
        if (request.getDob() != null) student.setDob(request.getDob());
        if (request.getPhone() != null) student.setPhone(request.getPhone());
        if (request.getDepartment() != null) student.setDepartment(request.getDepartment());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id + " not found"));

        studentRepository.delete(student);
    }
}
