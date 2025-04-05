package com.yourcompany.yourproject.repository;

import com.yourcompany.yourproject.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    List<Mark> findByStudentId(Integer studentId);
    List<Mark> findByCourseId(Integer courseId);
}
