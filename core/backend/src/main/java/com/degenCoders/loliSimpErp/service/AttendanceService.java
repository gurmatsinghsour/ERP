package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.degenCoders.loliSimpErp.Entity.Attendance;
import com.degenCoders.loliSimpErp.Entity.Student;
import com.degenCoders.loliSimpErp.repository.AttendanceRepository;
import com.degenCoders.loliSimpErp.repository.StudentRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repo;

    @Autowired
    private StudentRepository studentRepo;

    public List<Attendance> getByStudentId(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return repo.findByStudent(student);
    }

    public Attendance save(Attendance att) {
        return repo.save(att);
    }
}
