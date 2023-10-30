package com.task.wex.model.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTransactionResponse {

    private long id;
    private String description;
    private LocalDate transactionDate;
    private String originalPurchaseAmount;
    private double exchangeRate;
    private String convertedPurchaseAmount;
    private String message;
}
