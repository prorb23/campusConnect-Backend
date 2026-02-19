package com.rbslayer.campusconnectbackend.service.impl;

import com.rbslayer.campusconnectbackend.dto.response.JobApplicantResponse;
import com.rbslayer.campusconnectbackend.entity.Job;
import com.rbslayer.campusconnectbackend.entity.JobApplication;
import com.rbslayer.campusconnectbackend.entity.Student;
import com.rbslayer.campusconnectbackend.exception.DuplicateResourceException;
import com.rbslayer.campusconnectbackend.exception.ResourceNotFoundException;
import com.rbslayer.campusconnectbackend.repository.JobApplicationRepository;
import com.rbslayer.campusconnectbackend.repository.JobRepository;
import com.rbslayer.campusconnectbackend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JobApplicationService {
    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final StudentRepository studentRepository;

    public JobApplicationService(
            JobRepository jobRepository,
            JobApplicationRepository jobApplicationRepository,
            StudentRepository studentRepository
    ) {
        this.jobRepository = jobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.studentRepository = studentRepository;
    }

    public void apply(Long jobId, Long userId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + jobId));
        if(!job.isActive())
            throw new IllegalStateException("Job is not active.");

        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() ->
                new ResourceNotFoundException("student not found with id: " + userId));

        if(jobApplicationRepository.existsByJobIdAndStudentId(jobId, student.getId()))
            throw new DuplicateResourceException("Already applied to this job");

        JobApplication application = new JobApplication(job, student);
        jobApplicationRepository.save(application);
    }

    public List<JobApplicantResponse> getApplicants(Long jobId, Long recruiterUserId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + jobId));

        if(!job.getRecruiter().getUser().getId().equals(recruiterUserId))
            throw new IllegalStateException("Access denied.");

        return jobApplicationRepository.findByJobId(jobId)
                .stream()
                .map(app -> new JobApplicantResponse(
                        app.getId(),
                        app.getStudent().getId(),
                        app.getStudent().getFullName(),
                        app.getStudent().getUser().getEmail(),
                        app.getStatus(),
                        app.getAppliedAt()
                ))
                .toList();
    }
}
