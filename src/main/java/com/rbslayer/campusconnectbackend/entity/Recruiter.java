package com.rbslayer.campusconnectbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruiters")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String companyEmail;

    private String companyWebsite;
    private String location;

    @Column(nullable = false)
    private boolean verified = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Recruiter(String companyName, String companyEmail, User user) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.user = user;
    }

    public void updateProfile(String companyName, String website, String location) {
        this.companyName = companyName;
        this.companyWebsite = website;
        this.location = location;
    }

    public void verify() {
        this.verified = true;
    }

    public void revokeVerification() {
        this.verified = false;
    }
}
