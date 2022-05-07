package io.github.mayconfrr.bankline.controller;

import io.github.mayconfrr.bankline.dto.TransactionDto;
import io.github.mayconfrr.bankline.model.Transaction;
import io.github.mayconfrr.bankline.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clients/{clientId}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions(@PathVariable("clientId") Long clientId) {
        return transactionService.getAllTransactions(clientId);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("clientId") Long clientId,
                                                          @PathVariable("transactionId") Long transactionId) {
        return ResponseEntity.of(transactionService.getTransactionById(clientId, transactionId));
    }

    @PostMapping
    public ResponseEntity<Void> saveTransaction(@PathVariable("clientId") Long clientId,
                                                @RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionService.saveTransaction(clientId, transactionDto);

        return ResponseEntity.created(
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{transactionId}")
                        .build(clientId, transaction.getId())
        ).build();
    }
}