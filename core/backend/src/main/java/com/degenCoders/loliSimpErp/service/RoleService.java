package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.degenCoders.loliSimpErp.Entity.Roles;
import com.degenCoders.loliSimpErp.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public List<Roles> getAll() {
        return repo.findAll();
    }

    public Roles getByName(Roles name) {
        return repo.findByRoleName(name);
    }
}
