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

    @NotNull(message = "Graduation year is required")
    @Min(value = 2000, message = "Graduation year must be >= 2000")
    @Max(value = 2100, message = "Graduation year must be <= 2100")
    private Integer graduationYear;

}
