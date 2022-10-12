package com.salapp.studentservices.services;

import com.salapp.studentservices.exceptions.StudentNotFoundException;
import com.salapp.studentservices.model.Student;
import com.salapp.studentservices.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) {

        return this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
}
