package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseCode(String courseCode);
}