package com.rbslayer.campusconnectbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
