package io.github.mayconfrr.bankline.dto;

import java.io.Serializable;

public record ClientDto(String cpf, String name) implements Serializable {
}
