package net.pelozo.parcial.leonardo.velozo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "currency cannnot be null")
    private TypeCurrency currency;

    @DecimalMin(value = "0.0", message = "amount cannot be negative")
    private Float amount;
}
