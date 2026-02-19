package com.rbslayer.campusconnectbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length =  3000)
    private String description;

    @Column(nullable = false)
    private String skillsRequired;

    @Column(nullable = false)
    private String jobType;

    private String location;

    private String salaryRange;

    @Column(nullable = false)
    private LocalDate lastDateToApply;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applications = new ArrayList<>();


    public Job(String title, String description, String skillsRequired, String jobType, LocalDate lastDateToApply, Recruiter recruiter) {
        this.title = title;
        this.description = description;
        this.skillsRequired = skillsRequired;
        this.jobType = jobType;
        this.lastDateToApply = lastDateToApply;
        this.recruiter = recruiter;
    }

    public void deactivate() {
        this.active = false;
    }
}
