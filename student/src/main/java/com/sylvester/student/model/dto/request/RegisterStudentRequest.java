package com.sylvester.student.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterStudentRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Password is required")
    private String password;
}
