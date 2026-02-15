package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.response.EnrollmentResponse;
import com.rbslayer.campusconnectbackend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
@PreAuthorize("hasRole('STUDENT')")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}")
    public EnrollmentResponse enroll(@PathVariable Long courseId) {
        return enrollmentService.enrollInCourse(courseId);
    }

    @GetMapping("/me")
    public List<EnrollmentResponse> myEnrollments() {
        return enrollmentService.getMyEnrollments();
    }
}
