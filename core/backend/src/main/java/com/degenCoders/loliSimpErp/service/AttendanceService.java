package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.degenCoders.loliSimpErp.Entity.Attendance;
import com.degenCoders.loliSimpErp.Entity.Student;
import com.degenCoders.loliSimpErp.repository.AttendanceRepository;
import com.degenCoders.loliSimpErp.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




@Service
public class AttendanceService {


    @Autowired
    private AttendanceRepository repo;


    public Page<Attendance> getAllAttendance(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Attendance save(Attendance att) {
        return repo.save(att);
    }
}
