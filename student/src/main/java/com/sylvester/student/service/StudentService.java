package com.sylvester.student.service;

import com.sylvester.student.Authentication.AuthenticationRequest;
import com.sylvester.student.Authentication.AuthenticationResponse;
import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.request.UpdateStudentRequest;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;
import com.sylvester.student.model.entity.Student;

import java.util.List;

public interface StudentService {
    RegisterStudentResponse registerStudent(RegisterStudentRequest registerStudentRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    // New method to get all students
    List<Student> getAllStudents();

    // Get a student by ID
    Student getStudentById(Long id);
    //  Update a student by ID
    Student updateStudent(Long id, UpdateStudentRequest request);

    //delete a Student
    void deleteStudent(Long id);

}


