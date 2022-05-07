package io.github.mayconfrr.bankline.repository;

import io.github.mayconfrr.bankline.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByClientId(Long clientId);
    Optional<Transaction> findByClientIdAndId(Long clientId, Long id);
}
