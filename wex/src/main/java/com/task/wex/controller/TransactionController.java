package com.task.wex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.wex.model.entity.TransactionEntity;
import com.task.wex.model.request.PostTransactionRequest;
import com.task.wex.model.response.GetTransactionResponse;
import com.task.wex.service.GetTransactionService;
import com.task.wex.service.PostTransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private PostTransactionService postTransactionService;

    @Autowired
    private GetTransactionService getTransactionService;

    @PostMapping(value="/transactions")
    public ResponseEntity<TransactionEntity> postTransaction(@RequestBody PostTransactionRequest request) {
        return postTransactionService.postTransaction(request);
    }

    @GetMapping(value = "/transactions/{id}")
    public ResponseEntity<GetTransactionResponse> getTransaction(@PathVariable("id") Integer id, @RequestHeader(value = "currency", required = true) String currency) throws Exception {
        return getTransactionService.getTransaction(id, currency);
    }
    
    
}
