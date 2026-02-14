package com.rbslayer.campusconnectbackend.controller;

import com.rbslayer.campusconnectbackend.dto.request.CourseCreateRequest;
import com.rbslayer.campusconnectbackend.dto.response.CourseResponse;
import com.rbslayer.campusconnectbackend.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/courses")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCourseController {
    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(
            @Valid  @RequestBody CourseCreateRequest request
    ){
        return courseService.createCourse(request);
    }
}
