package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(Users username);
    Users findByEmail(Users email);
}
