package com.rbslayer.campusconnectbackend.service.impl;

import com.rbslayer.campusconnectbackend.dto.request.StudentCreateRequest;
import com.rbslayer.campusconnectbackend.dto.request.StudentUpdateRequest;
import com.rbslayer.campusconnectbackend.dto.response.StudentResponse;
import com.rbslayer.campusconnectbackend.entity.Student;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.exception.DuplicateResourceException;
import com.rbslayer.campusconnectbackend.repository.StudentRepository;
import com.rbslayer.campusconnectbackend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public StudentResponse createStudent(StudentCreateRequest request) {
        if(studentRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        if(studentRepository.existsByPhone(request.getPhone())){
            throw new DuplicateResourceException("Phone number already exists");
        }
        User authenticatedUser =(User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Student student = Student.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .college(request.getCollege())
                .graduationYear(request.getGraduationYear())
                .user(authenticatedUser)
                .build();

        Student savedStudent = studentRepository.save(student);
        return mapToResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResolutionException("Student not found with id: " + id)
                );
        return mapToResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResolutionException("Student not found with id: " + id)
                );

        if(!student.getPhone().equals(request.getPhone())
            && studentRepository.existsByPhone(request.getPhone()) ){
            throw new DuplicateResourceException("Phone number already exists: " + request.getPhone());
        }

        student.setFullName(request.getFullName());
        student.setPhone(request.getPhone());
        student.setCollege(request.getCollege());
        student.setGraduationYear(request.getGraduationYear());

        Student updatedStudent = studentRepository.save(student);
        return mapToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResolutionException("Student not found with id: " + id)
                );

        studentRepository.delete(student);
    }

    private StudentResponse mapToResponse(Student student){
        return StudentResponse.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .college(student.getCollege())
                .graduationYear(student.getGraduationYear())
                .build();
    }
}
