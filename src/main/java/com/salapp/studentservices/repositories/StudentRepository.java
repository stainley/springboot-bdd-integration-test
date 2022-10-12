package com.salapp.studentservices.repositories;

import com.salapp.studentservices.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByName(String name);

    // Custom JPQL query
    @Query(value = "SELECT s FROM Student s")
    List<Student> getAllStudentsCustom();

    @Query(value = "select avg (s.grade) FROM Student s WHERE s.active = true")
    Double getAvgGradeForActiveStudents();
}
