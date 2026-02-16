package com.rbslayer.campusconnectbackend.repository;

import com.rbslayer.campusconnectbackend.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
    Optional<Recruiter> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    boolean existsByCompanyEmail(String companyEmail);
}
