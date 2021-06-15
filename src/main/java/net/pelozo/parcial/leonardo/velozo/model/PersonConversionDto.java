package net.pelozo.parcial.leonardo.velozo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonConversionDto {

    private String name;
    private TypeCurrency currency;
    private Float amount;


}
