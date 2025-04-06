package com.degenCoders.loliSimpErp.service;

import com.degenCoders.loliSimpErp.Entity.Course;
import com.degenCoders.loliSimpErp.Entity.Marks;
import com.degenCoders.loliSimpErp.Entity.Student;
import com.degenCoders.loliSimpErp.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkRepository repo;

    public List<Marks> getByStudentId(Student student) {
        return repo.findByStudentId(student);
    }

    public List<Marks> getByCourseId(Course course) {
        return repo.findByCourseId(course);
    }

    public Marks save(Marks mark) {
        return repo.save(mark);
    }

    public List<Marks> saveAll(List<Marks> marksList) {
        return repo.saveAll(marksList);
    }
}
