package com.test.wex.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.repository.TransactionRepository;
import com.task.wex.service.PostTransactionService;

@ExtendWith(MockitoExtension.class)
class PostTransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private PostTransactionService postTest;
    
    @Test
    void postTest() {
        var request = PostTransactionRequest.builder()
        .description("a")
        .purchaseAmount(1.1)
        .transactionDate(LocalDate.now())
        .build();
        when(transactionRepository.save(any())).thenReturn(TransactionEntity.builder().build());
        var response = postTest.postTransaction(request);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void validationTest() {
        var request = PostTransactionRequest.builder()
        .description(null)
        .purchaseAmount(1.1)
        .transactionDate(LocalDate.now())
        .build();
        var response = postTest.postTransaction(request);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}