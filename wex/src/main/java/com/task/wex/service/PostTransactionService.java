package com.task.wex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.wex.exception.OutOfPatternException;
import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.repository.TransactionRepository;
import com.task.wex.util.MapperUtils;
import com.task.wex.util.ValidationUtils;

@Service
public class PostTransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<TransactionEntity> postTransaction(PostTransactionRequest request) {
        try {
            ValidationUtils.validatePostRequest(request);
            var entity = MapperUtils.toTransactionEntity(request);
            return new ResponseEntity<TransactionEntity>(transactionRepository.save(entity), null, HttpStatus.CREATED);
        } catch (OutOfPatternException e) {
            var response = TransactionEntity.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
        }
    }
}