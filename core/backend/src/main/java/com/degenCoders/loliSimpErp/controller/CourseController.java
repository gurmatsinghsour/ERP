package com.degenCoders.loliSimpErp.controller;

import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping
    public ResponseEntity<Object> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.create(course));
    }
}
