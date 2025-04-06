package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(Roles roleName);
}