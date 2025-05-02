package com.degenCoders.loliSimpErp.repository;


import com.degenCoders.loliSimpErp.Entity.Attendance;
import com.degenCoders.loliSimpErp.Entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AttendanceRepository extends JpaRepository<Attendance, Integer>, PagingAndSortingRepository<Attendance, Integer> {
}

