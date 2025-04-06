package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public List<Course> getAll() {
        return repo.findAll();
    }

    public Course getByCode(String code) {
        return repo.findByCourseCode(code);
    }
}
