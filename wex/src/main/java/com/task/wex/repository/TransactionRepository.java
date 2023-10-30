package com.task.wex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.task.wex.model.entity.TransactionEntity;

@Service
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    
}
