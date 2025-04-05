package com.yourcompany.yourproject.service;

import com.yourcompany.yourproject.model.Student;
import com.yourcompany.yourproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student updatedStudent) {
        updatedStudent.setStudentId(id);
        return studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
