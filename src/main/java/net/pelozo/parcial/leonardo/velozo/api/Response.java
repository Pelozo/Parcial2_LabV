package net.pelozo.parcial.leonardo.velozo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    List<CurrencyApiModel> currencyList;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CurrencyApiModel{
       FfsThisApi casa;


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class FfsThisApi{
            String compra;
        }
    }
}
