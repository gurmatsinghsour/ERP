package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Predictions;
import com.degenCoders.loliSimpErp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Predictions, Long> {

    // ✅ This is the method you must declare:
    List<Predictions> findByStudent(Student student);
}
