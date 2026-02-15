package com.rbslayer.campusconnectbackend.controller.Admin;

import com.rbslayer.campusconnectbackend.dto.response.EnrollmentResponse;
import com.rbslayer.campusconnectbackend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/enrollments")
@PreAuthorize("hasRole('ADMIN')")
public class AdminEnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
