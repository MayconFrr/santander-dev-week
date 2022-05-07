package io.github.mayconfrr.bankline.service;

import io.github.mayconfrr.bankline.dto.TransactionDto;
import io.github.mayconfrr.bankline.model.Client;
import io.github.mayconfrr.bankline.model.Transaction;
import io.github.mayconfrr.bankline.repository.ClientRepository;
import io.github.mayconfrr.bankline.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(Long clientId) {
        return transactionRepository.findAllByClientId(clientId);
    }

    public Optional<Transaction> getTransactionById(Long clientId, Long transactionId) {
        return transactionRepository.findByClientIdAndId(clientId, transactionId);
    }

    @Transactional
    public Transaction saveTransaction(Long clientId, TransactionDto transactionDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        Transaction transaction = Transaction.builder()
                .client(client)
                .dateTime(LocalDateTime.now())
                .description(transactionDto.description())
                .type(transactionDto.type())
                .value(switch (transactionDto.type()) {
                    case INCOME -> transactionDto.value().abs();
                    case EXPENSE -> transactionDto.value().abs().negate();
                }).build();

        client.getAccount().setBalance(client.getAccount().getBalance().add(transaction.getValue()));
        clientRepository.save(client);

        return transactionRepository.save(transaction);
    }
}
