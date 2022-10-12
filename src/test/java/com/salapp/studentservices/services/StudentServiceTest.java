package com.salapp.studentservices.services;

import com.salapp.studentservices.exceptions.StudentNotFoundException;
import com.salapp.studentservices.model.Student;
import com.salapp.studentservices.repositories.StudentRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentsById_forSavedStudent_isReturned() {
        // given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));
        // when
        Student student = studentService.getStudentById(savedStudent.getId());

        // then
        then(student.getId()).isNotNull();
    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown() {
        // given
        Long id = 999L;

        // when
        Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));

        // then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }

}
