package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.request.JobCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.JobResponse;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @PreAuthorize("hasRole('RECRUITER')")
    public JobResponse createJob(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody JobCreateRequest request
            ){
        return jobService.createJob(user.getId(), request);
    }

    @GetMapping
    public List<JobResponse> getAllActiveJobs(){
        return jobService.getActiveJobs();
    }
}
