package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.request.RecruiterCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.RecruiterResponse;
import com.rbslayer.campusconnectbackend.entity.Recruiter;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.exception.DuplicateResourceException;
import com.rbslayer.campusconnectbackend.exception.ResourceNotFoundException;
import com.rbslayer.campusconnectbackend.repository.RecruiterRepository;
import com.rbslayer.campusconnectbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;

    public RecruiterService(
            RecruiterRepository recruiterRepository,
            UserRepository userRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
    }

    public RecruiterResponse register(Long userId, RecruiterCreateRequest request) {
        if(recruiterRepository.existsByUserId(userId)) {
            throw new DuplicateResourceException("Recruiter already exists");
        }

        if (recruiterRepository.existsByCompanyEmail(request.getCompanyEmail()))
            throw new IllegalStateException("Company email already registered");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Recruiter recruiter = new Recruiter(
                request.getCompanyName(),
                request.getCompanyEmail(),
                user
        );

        recruiter.updateProfile(
                request.getCompanyName(),
                request.getCompanyWebsite(),
                request.getLocation()
        );

        recruiterRepository.save(recruiter);
        return map(recruiter);
    }

    public RecruiterResponse me(Long userId) {
        return recruiterRepository.findByUserId(userId)
                .map(this::map)
                .orElseThrow(() -> new IllegalStateException("Recruiter not found"));
    }

    public RecruiterResponse update(Long userId, RecruiterCreateRequest req) {

        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Recruiter not found"));

        recruiter.updateProfile(
                req.getCompanyName(),
                req.getCompanyWebsite(),
                req.getLocation()
        );

        return map(recruiter);
    }

    private RecruiterResponse map(Recruiter r) {
        return new RecruiterResponse(
                r.getId(),
                r.getCompanyName(),
                r.getCompanyEmail(),
                r.getCompanyWebsite(),
                r.getLocation(),
                r.isVerified()
        );
    }
}
