package io.github.mayconfrr.bankline.repository;

import io.github.mayconfrr.bankline.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
