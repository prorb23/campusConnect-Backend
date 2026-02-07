package com.rbslayer.campusconnectbackend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 80)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10 digit indian number"
    )
    private String phone;

    @NotBlank(message = "College name is required")
    private String college;

    @NotNull(message = "Graduation year is required")
    @Min(value = 2000, message = "Graduation year must be >= 2000")
    @Max(value = 2100, message = "Graduation year must be <= 2100")
    private Integer graduationYear;

}
