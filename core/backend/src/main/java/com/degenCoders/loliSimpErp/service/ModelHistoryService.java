package com.degenCoders.loliSimpErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.degenCoders.loliSimpErp.Entity.ModelHistory;
import com.degenCoders.loliSimpErp.repository.ModelHistoryRepository;

@Service
public class ModelHistoryService {

    @Autowired
    private ModelHistoryRepository repo;

    public ModelHistory getByVersion(String version) {
        return repo.findByVersionName(version);
    }

    public List<ModelHistory> getAll() {
        return repo.findAll();
    }
}
