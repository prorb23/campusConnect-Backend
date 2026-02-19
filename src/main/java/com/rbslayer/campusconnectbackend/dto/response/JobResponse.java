package com.rbslayer.campusconnectbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class JobResponse {
    Long id;
    String title;
    String description;
    String skillsRequired;
    String jobType;
    String location;
    String salaryRange;
    LocalDate lastDateToApply;
    boolean active;
}
