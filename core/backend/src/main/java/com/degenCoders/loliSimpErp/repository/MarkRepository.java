package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.Entity.Marks;
import com.degenCoders.loliSimpErp.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByStudentId(Student studentId);
    List<Marks> findByCourseId(Course courseId);
}
