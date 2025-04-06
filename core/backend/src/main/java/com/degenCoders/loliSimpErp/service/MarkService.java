package com.degenCoders.loliSimpErp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degenCoders.loliSimpErp.repository.MarkRepository;
import com.degenCoders.loliSimpErp.Entity.Marks;
import com.degenCoders.loliSimpErp.Entity.Student;

@Service
public class MarkService {

    @Autowired
    private MarkRepository repo;

    public List<Marks> getByStudentId(Student studentId) {
        return repo.findByStudentId(studentId);
    }

    public Marks save(Marks mark) {
        return repo.save(mark);
    }

    
}
