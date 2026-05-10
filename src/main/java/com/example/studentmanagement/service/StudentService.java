package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
