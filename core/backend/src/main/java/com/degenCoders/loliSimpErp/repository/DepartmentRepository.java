package com.yourcompany.yourproject.repository;

import com.yourcompany.yourproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByCode(String code);
}