package com.sylvester.student.service;

import com.sylvester.student.Authentication.AuthenticationRequest;
import com.sylvester.student.Authentication.AuthenticationResponse;
import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;

public interface AuthenticationService {

    RegisterStudentResponse registerStudent(RegisterStudentRequest registerStudentRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
