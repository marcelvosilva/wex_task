package com.test.wex.util;

import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.task.wex.exception.OutOfPatternException;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.util.ValidationUtils;

class ValidationUtilsTest {
    
    @Test
    void validatePostRequestDescriptionNullTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description(null)
            .build());
        });
    }

    @Test
    void validatePostRequestDescriptionEmptyTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("")
            .build());
        });
    }

    @Test
    void validatePostRequestDescriptionAboveFiftyTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            .build());
        });
    }

    @Test
    void validatePostRequestTransactionDateNullTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("a")
            .transactionDate(null)
            .build());
        });
    }

    @Test
    void validatePostRequestPurchaseAmountNullTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("a")
            .transactionDate(LocalDate.now())
            .purchaseAmount(null)
            .build());
        });
    }

    @Test
    void validatePostRequestPurchaseAmountNaNTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("a")
            .transactionDate(LocalDate.now())
            .purchaseAmount(Double.NaN)
            .build());
        });
    }

    @Test
    void validatePostRequestPurchaseAmountBelowZeroTest() {
        assertThrows(OutOfPatternException.class, () -> {
            ValidationUtils.validatePostRequest(PostTransactionRequest.builder()
            .description("a")
            .transactionDate(LocalDate.now())
            .purchaseAmount(-0.2)
            .build());
        });
    }
}
