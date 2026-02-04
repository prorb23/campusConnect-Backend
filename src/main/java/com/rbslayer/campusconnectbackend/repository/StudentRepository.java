package com.rbslayer.campusconnectbackend.repository;

import com.rbslayer.campusconnectbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
