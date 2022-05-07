package io.github.mayconfrr.bankline.dto;

import io.github.mayconfrr.bankline.model.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransactionDto(String description, BigDecimal value, TransactionType type) implements Serializable {
}
