package com.task.wex.util;

import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.model.response.GetTransactionResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtils {
    
    public static TransactionEntity toTransactionEntity(PostTransactionRequest request) {
        return TransactionEntity.builder()
        .description(request.getDescription())
        .purchaseAmount(CurrencyUtils.round(request.getPurchaseAmount()))
        .transactionDate(request.getTransactionDate())
        .build();
    }    

    public static GetTransactionResponse toGetTransactionResponse(TransactionEntity entity, double exchangeRate) {
        return GetTransactionResponse.builder()
        .description(entity.getDescription())
        .exchangeRate(exchangeRate)
        .originalPurchaseAmount(entity.getPurchaseAmount())
        .id(entity.getId())
        .transactionDate(entity.getTransactionDate())
        .build();
    }
}
