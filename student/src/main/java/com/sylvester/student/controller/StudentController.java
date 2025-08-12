package com.sylvester.student.controller;

import com.sylvester.student.model.dto.request.RegisterStudentRequest;
import com.sylvester.student.model.dto.request.UpdateStudentRequest;
import com.sylvester.student.model.dto.response.RegisterStudentResponse;
import com.sylvester.student.model.entity.Student;
import com.sylvester.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping ("/api/v1")
@RestController
@RequiredArgsConstructor

public class StudentController {
     private final StudentService studentService;
//update the student entity class to have a password field
    //drop your table (delete a table) how to drop table,
    //implement spring security on this application.
//registers student
     @PostMapping ("/signup")
     public ResponseEntity<RegisterStudentResponse> register(@RequestBody @Valid RegisterStudentRequest registerStudentRequest) {
        return ResponseEntity.ok(studentService.registerStudent(registerStudentRequest));
     }

//get all students
     @GetMapping("/students")
     public ResponseEntity<List<Student>> getAllStudents() {
         List<Student> students = studentService.getAllStudents();
         return ResponseEntity.ok(students);
     }
     // GET STUDENTS ID
     @GetMapping("/students/{id}")
     public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
         Student student = studentService.getStudentById(id);
         return ResponseEntity.ok(student);
     }
     // to edit students
     @PutMapping("/students/{id}")
     public ResponseEntity<Student> updateStudent(
             @PathVariable Long id,
             @RequestBody UpdateStudentRequest request) {
         Student updatedStudent = studentService.updateStudent(id, request);
         return ResponseEntity.ok(updatedStudent);
     }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
    }

}
