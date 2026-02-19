package com.rbslayer.campusconnectbackend.dto.response;

import com.rbslayer.campusconnectbackend.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record JobApplicantResponse(
        Long applicationId,
        Long studentId,
        String studentName,
        String studentEmail,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {}
