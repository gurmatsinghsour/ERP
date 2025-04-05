package com.yourcompany.yourproject.repository;

import com.yourcompany.yourproject.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction, Integer> {
    List<Prediction> findByStudentId(Integer studentId);
}
