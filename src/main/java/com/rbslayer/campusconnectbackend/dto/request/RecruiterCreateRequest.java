package com.rbslayer.campusconnectbackend.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecruiterCreateRequest {
    @NotBlank
    private String companyName;

    @Email
    @NotBlank
    private String companyEmail;

    private String companyWebsite;
    private String location;
}
