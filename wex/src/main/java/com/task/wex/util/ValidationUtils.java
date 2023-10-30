package com.task.wex.util;

import org.springframework.util.StringUtils;

import com.task.wex.exception.OutOfPatternException;
import com.task.wex.model.request.PostTransactionRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {
    
    public static void validatePostRequest(PostTransactionRequest request) throws OutOfPatternException {
        if (!StringUtils.hasText(request.getDescription()) || request.getDescription().length() > 50) {
            throw new OutOfPatternException("description must not be null or empty and must have 50 characters at max");
        }

        if (request.getTransactionDate() == null) {
            throw new OutOfPatternException("transactionDate must not be null or empty and must follow the pattern yyyy-MM-dd");
        }

        if (request.getPurchaseAmount() == null || request.getPurchaseAmount().isNaN() || request.getPurchaseAmount() < 0) {
            throw new OutOfPatternException("purchaseAmount must not be null, empty, not a number or below 0");
        }
    }
}
