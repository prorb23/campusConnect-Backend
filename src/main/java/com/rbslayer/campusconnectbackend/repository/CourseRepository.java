package com.rbslayer.campusconnectbackend.repository;

import com.rbslayer.campusconnectbackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByActiveTrue();
}
