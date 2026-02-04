package com.rbslayer.campusconnectbackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {

    @NotBlank
    @Size(min = 2, max = 80)
    private String fullName;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String phone;

    @NotBlank
    private String college;

    @NotNull
    @Min(2000)
    @Max(2100)
    private Integer graduationYear;
}
