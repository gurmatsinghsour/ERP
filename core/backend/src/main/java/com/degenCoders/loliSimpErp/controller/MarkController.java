package com.degenCoders.loliSimpErp.controller;

import com.degenCoders.loliSimpErp.Entity.Marks;
import com.degenCoders.loliSimpErp.Entity.Student;
import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.repository.StudentRepository;
import com.degenCoders.loliSimpErp.repository.CourseRepository;
import com.degenCoders.loliSimpErp.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Marks>> getMarksByStudentId(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(markService.getByStudentId(student));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Marks>> getMarksByCourseId(@PathVariable Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return ResponseEntity.ok(markService.getByCourseId(course));
    }    

    @PostMapping
    public ResponseEntity<Marks> saveMark(@RequestBody Marks mark) {
        return ResponseEntity.ok(markService.save(mark));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Marks>> saveBulkMarks(@RequestBody List<Marks> marksList) {
        return ResponseEntity.ok(markService.saveAll(marksList));
    }
}
