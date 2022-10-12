package com.salapp.studentservices.controller;

import com.salapp.studentservices.exceptions.StudentNotFoundException;
import com.salapp.studentservices.model.Student;
import com.salapp.studentservices.services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {
        // given
        given(studentService.getStudentById(Mockito.anyLong())).willReturn(
                Student.builder().id(1L)
                        .name("Mark")
                        .grade(10)
                        .build()
        );

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Mark"))
                .andExpect(jsonPath("grade").value(10));
    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {
        // given
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);

        // when  // then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
