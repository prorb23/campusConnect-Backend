package com.rbslayer.campusconnectbackend.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean active;
    private LocalDateTime createdAt;
}
