package com.rbslayer.campusconnectbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class JobCreateRequest {
    @NotBlank
    public String title;
    @NotBlank
    public String description;
    @NotBlank
    public String skillsRequired;
    @NotBlank
    public String jobType;

    public String location;
    public String salaryRange;

    @NotNull
    public LocalDate lastDateToApply;

}
