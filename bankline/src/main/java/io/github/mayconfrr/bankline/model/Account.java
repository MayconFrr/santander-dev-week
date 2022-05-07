package io.github.mayconfrr.bankline.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Account {
    private Long number;
    private BigDecimal balance;
}