package com.degenCoders.loliSimpErp.service;

import com.degenCoders.loliSimpErp.Entity.Predictions;
import com.degenCoders.loliSimpErp.Entity.Student;
import com.degenCoders.loliSimpErp.repository.PredictionRepository;
import com.degenCoders.loliSimpErp.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionService {

    @Autowired
    private PredictionRepository repo;

    @Autowired
    private StudentRepository studentRepo;

    public List<Predictions> getByStudentId(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return repo.findByStudent(student);
    }

    public Predictions save(Predictions prediction) {
        return repo.save(prediction);
    }
}
