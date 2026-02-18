package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.request.JobCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.JobResponse;
import com.rbslayer.campusconnectbackend.entity.Job;
import com.rbslayer.campusconnectbackend.entity.Recruiter;
import com.rbslayer.campusconnectbackend.exception.ResourceNotFoundException;
import com.rbslayer.campusconnectbackend.repository.JobRepository;
import com.rbslayer.campusconnectbackend.repository.RecruiterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JobService {
    private final JobRepository jobRepository;
    private final RecruiterRepository recruiterRepository;

    public JobService(JobRepository jobRepository, RecruiterRepository recruiterRepository) {
        this.jobRepository = jobRepository;
        this.recruiterRepository = recruiterRepository;
    }

    public JobResponse createJob(long userId, JobCreateRequest request) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter profile not found"));
        if(!recruiter.isVerified())
            throw new IllegalStateException("Recruiter is not verified");

        Job job = new Job(
                request.title,
                request.description,
                request.skillsRequired,
                request.jobType,
                request.lastDateToApply,
                recruiter
        );

        jobRepository.save(job);
        return map(job);
    }

    public List<JobResponse> getActiveJobs() {
        return jobRepository.findByActiveTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    private JobResponse map(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkillsRequired(),
                job.getJobType(),
                job.getLocation(),
                job.getSalaryRange(),
                job.getLastDateToApply(),
                job.isActive()
        );
    }

}
