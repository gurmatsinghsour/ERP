package com.degenCoders.loliSimpErp.service;

import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.Entity.Department;
import com.degenCoders.loliSimpErp.repository.CourseRepository;
import com.degenCoders.loliSimpErp.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Course create(Course course) {
        String deptCode = course.getDepartmentId().getCode(); 
        Department department = departmentRepository.findByCode(deptCode);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        course.setDepartmentId(department);  
        return courseRepository.save(course);
        
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }
}
