package com.rbslayer.campusconnectbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruiterResponse {
    private Long id;
    private String companyName;
    private String companyEmail;
    private String companyWebsite;
    private String location;
    private boolean verified;
}
