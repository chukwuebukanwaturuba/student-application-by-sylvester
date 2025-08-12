package com.sylvester.student.model.dto.response;


import com.sylvester.student.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudentResponse {

    private String message;
    private Student student;
}
