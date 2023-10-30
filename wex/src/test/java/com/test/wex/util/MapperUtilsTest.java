package com.test.wex.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.model.response.GetTransactionResponse;
import com.task.wex.util.MapperUtils;

class MapperUtilsTest {
    
    @Test
    void toTransactionEntityTest() {
        var expected = TransactionEntity.builder()        
            .description("a")
            .purchaseAmount("1.10")
            .transactionDate(LocalDate.of(2023, 1, 1))
            .build();
        var request = PostTransactionRequest.builder()
            .description("a")
            .purchaseAmount(1.1)
            .transactionDate(LocalDate.of(2023, 1, 1))
            .build();
        
        assertEquals(expected, MapperUtils.toTransactionEntity(request));
    }

    @Test
    void toGetTransactionResponseTest() {
        var expected = GetTransactionResponse.builder()
            .description("a")
            .exchangeRate(2)
            .originalPurchaseAmount("1.10")
            .id(1)
            .transactionDate(LocalDate.of(2023, 1, 1))
            .build();
        var entity = TransactionEntity.builder()        
            .description("a")
            .id(1)
            .purchaseAmount("1.10")
            .transactionDate(LocalDate.of(2023, 1, 1))
            .build();
        assertEquals(expected, MapperUtils.toGetTransactionResponse(entity, 2));
    }
}
