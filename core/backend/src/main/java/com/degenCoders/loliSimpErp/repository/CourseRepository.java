package com.yourcompany.yourproject.repository;

import com.yourcompany.yourproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseCode(String courseCode);
}