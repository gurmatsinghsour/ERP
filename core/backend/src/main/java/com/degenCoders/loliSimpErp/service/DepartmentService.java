package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.degenCoders.loliSimpErp.Entity.Department;
import com.degenCoders.loliSimpErp.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public List<Department> getAll() {
        return repo.findAll();
    }

    public Optional<Department> getById(int id) {
        return repo.findById(id);
    }

    public Department create(Department d) {
        return repo.save(d);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
