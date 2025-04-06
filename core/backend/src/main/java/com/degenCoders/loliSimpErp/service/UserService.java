package com.degenCoders.loliSimpErp.service;

import com.degenCoders.loliSimpErp.Entity.Users;
import com.degenCoders.loliSimpErp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<Users> getAll() {
        return repo.findAll();
    }

    public Users getByUsername(Users username) {
        return repo.findByUsername(username);
    }

    public Users getByEmail(Users email) {
        return repo.findByEmail(email);
    }

    public Users create(Users user) {
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
