package com.test.wex.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.repository.TransactionRepository;
import com.task.wex.restclient.GetExchangeRateRestClient;
import com.task.wex.service.GetTransactionService;

@ExtendWith(MockitoExtension.class)
class GetTransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private GetExchangeRateRestClient getExchangeRateRestClient;

    @InjectMocks
    private GetTransactionService getTransactionService;
    
    @Test
    void getTest() throws Exception {
        when(transactionRepository.findById(anyInt())).thenReturn(Optional.of(TransactionEntity.builder()
            .description("a")
            .transactionDate(LocalDate.now())
            .purchaseAmount("10.00")
            .id(1)
            .build()));
        when(getExchangeRateRestClient.getExchangeRate(anyString(), any())).thenReturn("1.000");
        var response = getTransactionService.getTransaction(1, "Real");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    } 

    @Test
    void transactionNotFoundTest() throws Exception {
        when(transactionRepository.findById(anyInt())).thenReturn(Optional.empty());
        var response = getTransactionService.getTransaction(1, "Real");
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    } 

    @Test
    void exchangeRateNotFoundTest() throws Exception {
        when(transactionRepository.findById(anyInt())).thenReturn(Optional.of(TransactionEntity.builder().build()));
        when(getExchangeRateRestClient.getExchangeRate(anyString(), any())).thenReturn(null);
        var response = getTransactionService.getTransaction(1, "Real");
        assertNotNull(response);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    } 
}