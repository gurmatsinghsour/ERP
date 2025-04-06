package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Department;
import com.degenCoders.loliSimpErp.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDepartmentId(Department department); 
}
