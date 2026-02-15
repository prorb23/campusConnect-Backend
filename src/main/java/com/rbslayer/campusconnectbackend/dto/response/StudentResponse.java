package com.rbslayer.campusconnectbackend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String fullName;
    private String phone;
    private String college;
    private Integer graduationYear;
}
