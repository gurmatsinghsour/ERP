package com.yourcompany.yourproject.repository;

import com.yourcompany.yourproject.model.ModelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelHistoryRepository extends JpaRepository<ModelHistory, Integer> {
    ModelHistory findByVersionName(String versionName);
}