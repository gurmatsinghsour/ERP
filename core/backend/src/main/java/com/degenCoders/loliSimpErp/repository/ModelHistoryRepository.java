package com.degenCoders.loliSimpErp.repository;

import com.degenCoders.loliSimpErp.Entity.ModelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelHistoryRepository extends JpaRepository<ModelHistory, Integer> {
    ModelHistory findByVersionName(String versionName);
}