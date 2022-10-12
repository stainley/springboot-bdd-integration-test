package com.salapp.studentservices.controller;

import com.salapp.studentservices.model.Student;
import com.salapp.studentservices.services.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest
class StudentControllerBaseClass {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @BeforeEach
    void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        // given
        given(studentService.getStudentById(Mockito.anyLong())).willReturn(
                Student.builder().id(1L)
                        .name("Mark")
                        .grade(10)
                        .build()
        );
    }
}
