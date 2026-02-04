package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.request.StudentCreateRequest;
import com.rbslayer.campusconnectbackend.dto.request.StudentUpdateRequest;
import com.rbslayer.campusconnectbackend.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest request);
    StudentResponse getStudentById(Long id);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStudent(Long id, StudentUpdateRequest request);
    void deleteStudent(Long id);
}
