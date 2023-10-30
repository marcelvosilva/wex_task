package com.task.wex.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.wex.model.response.GetTransactionResponse;
import com.task.wex.repository.TransactionRepository;
import com.task.wex.restclient.GetExchangeRateRestClient;
import com.task.wex.util.CurrencyUtils;
import com.task.wex.util.MapperUtils;

@Service
public class GetTransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private GetExchangeRateRestClient getExchangeRateRestClient;

    public ResponseEntity<GetTransactionResponse> getTransaction(Integer id, String currency) throws Exception {
        try {
            var entity = transactionRepository.findById(id);
            var exchangeRate = getExchangeRateRestClient.getExchangeRate(currency, entity.get().getTransactionDate());
            if (exchangeRate == null) {
                var message = GetTransactionResponse.builder().message("Exchange rate not found").build();
                return new ResponseEntity<GetTransactionResponse>(message, null, HttpStatus.UNPROCESSABLE_ENTITY);
            }
            var response = MapperUtils.toGetTransactionResponse(entity.get(), Double.valueOf(exchangeRate));
            response.setConvertedPurchaseAmount(CurrencyUtils.round(Double.valueOf(response.getOriginalPurchaseAmount()) * response.getExchangeRate()));
            return ResponseEntity.ok(response);
            
        } catch (NoSuchElementException e) {
            var body = GetTransactionResponse.builder().message("Transaction not found").build();
            return new ResponseEntity<GetTransactionResponse>(body, null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw e;
        }
    }
}