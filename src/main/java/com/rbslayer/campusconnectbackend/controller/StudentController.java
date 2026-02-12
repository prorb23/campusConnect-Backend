package com.rbslayer.campusconnectbackend.controller;
import com.rbslayer.campusconnectbackend.dto.request.StudentCreateRequest;
import com.rbslayer.campusconnectbackend.dto.request.StudentUpdateRequest;
import com.rbslayer.campusconnectbackend.dto.response.StudentResponse;
import com.rbslayer.campusconnectbackend.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(
            @Valid
            @RequestBody StudentCreateRequest request
    ){
        return studentService.createStudent(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/{id}")
    public StudentResponse updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentUpdateRequest request){
        return studentService.updateStudent(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}
