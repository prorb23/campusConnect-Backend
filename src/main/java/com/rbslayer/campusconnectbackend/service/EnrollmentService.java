package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.response.EnrollmentResponse;
import com.rbslayer.campusconnectbackend.entity.Course;
import com.rbslayer.campusconnectbackend.entity.Enrollment;
import com.rbslayer.campusconnectbackend.entity.Student;
import com.rbslayer.campusconnectbackend.exception.DuplicateResourceException;
import com.rbslayer.campusconnectbackend.exception.ResourceNotFoundException;
import com.rbslayer.campusconnectbackend.repository.CourseRepository;
import com.rbslayer.campusconnectbackend.repository.EnrollmentRepository;
import com.rbslayer.campusconnectbackend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentResponse enrollInCourse(Long courseId){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (enrollmentRepository.findByStudentIdAndCourseId(student.getId(), courseId).isPresent()){
            throw new DuplicateResourceException("Already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return  mapToResponse(savedEnrollment);
    }

    public List<EnrollmentResponse> getMyEnrollments(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return enrollmentRepository.findByStudentId(student.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<EnrollmentResponse> getAllEnrollments(){
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment){
        return EnrollmentResponse.builder()
                .enrollmentId(enrollment.getId())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .status(enrollment.getStatus())
                .enrolledAt(enrollment.getEnrolledAt())
                .build();
    }
}
