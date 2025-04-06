package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByCode(String code);
}