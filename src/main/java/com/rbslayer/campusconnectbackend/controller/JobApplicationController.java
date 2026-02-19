package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.response.JobApplicantResponse;
import com.rbslayer.campusconnectbackend.entity.JobApplication;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.service.impl.JobApplicationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobApplicationController {
    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping("/{jobId}/apply")
    @PreAuthorize("hasRole('STUDENT')")
    public void apply(
            @PathVariable Long jobId,
            @AuthenticationPrincipal User user
    ){
        service.apply(jobId, user.getId());
    }

    @GetMapping("/{jobId}/applications")
    @PreAuthorize("hasRole('RECRUITER')")
    public List<JobApplicantResponse> getApplicants(
            @PathVariable Long jobId,
            @AuthenticationPrincipal User user
    ){
        return service.getApplicants(jobId, user.getId());
    }
}
