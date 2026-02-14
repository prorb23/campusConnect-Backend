package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.response.CourseResponse;
import com.rbslayer.campusconnectbackend.service.CourseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllActiveCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
}
