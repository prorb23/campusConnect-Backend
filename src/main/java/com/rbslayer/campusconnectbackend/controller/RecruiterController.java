package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.request.RecruiterCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.RecruiterResponse;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterResponse registerRecruiter(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody RecruiterCreateRequest request
    ) {
        return recruiterService.register(user.getId(), request);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterResponse getMyProfile(
            @AuthenticationPrincipal User user
    ) {
        return recruiterService.me(user.getId());
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterResponse updateMyProfile(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody RecruiterCreateRequest request
    ) {
        return recruiterService.update(user.getId(), request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RecruiterResponse> getAllRecruiters(){
        return recruiterService.getAllRecruiters();
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public RecruiterResponse verifyRecruiter(@PathVariable Long id) {
        return recruiterService.verifyRecruiter(id);
    }

}
