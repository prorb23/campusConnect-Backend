package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.request.CourseCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.CourseResponse;
import com.rbslayer.campusconnectbackend.entity.Course;
import com.rbslayer.campusconnectbackend.exception.ResourceNotFoundException;
import com.rbslayer.campusconnectbackend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CourseCreateRequest request){
        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        Course savedCourse = courseRepository.save(course);
        return mapToResponse(savedCourse);
    }

    public List<CourseResponse> getAllActiveCourses(){
        return courseRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CourseResponse getCourseById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id " + id));
        return mapToResponse(course);
    }

    private CourseResponse mapToResponse(Course course){
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .active(course.isActive())
                .createdAt(course.getCreatedAt())
                .build();
    }
}
