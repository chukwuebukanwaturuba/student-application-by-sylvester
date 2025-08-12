package com.sylvester.student.repository;

import java.util.Optional;
import com.sylvester.student.model.entity.Student;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    Optional<Object> findByPhone(@NotBlank(message = "Phone is required") String phone);
}

