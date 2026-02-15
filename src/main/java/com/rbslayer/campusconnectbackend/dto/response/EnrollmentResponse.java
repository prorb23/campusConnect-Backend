package com.rbslayer.campusconnectbackend.dto.response;

import com.rbslayer.campusconnectbackend.entity.EnrollmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrollmentResponse {
    private Long enrollmentId;
    private Long courseId;
    private String courseTitle;
    private EnrollmentStatus status;
    private LocalDateTime enrolledAt;
}
