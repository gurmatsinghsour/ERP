package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Predictions;
import com.degenCoders.loliSimpErp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Predictions, Long> {
    List<Predictions> findByStudent(Student student);
}
