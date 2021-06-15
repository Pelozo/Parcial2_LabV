package net.pelozo.parcial.leonardo.velozo.api;

import com.google.gson.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import net.pelozo.parcial.leonardo.velozo.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Service
@Slf4j
public class ApiCurrencyExchangeService {

    @CircuitBreaker(name="exchange", fallbackMethod = "fallback")
    public Float getExchangeEur() throws IOException, InterruptedException, ParseException {

        //objeto a enviar a la API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        //llamado a la API
        HttpResponse<String> responseDolar = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        String valor = JsonParser.parseString(responseDolar.body()).getAsJsonArray().get(0).getAsJsonObject().get("dolar").getAsJsonObject().get("compra").getAsString();


        //20 minutos porque la api me trae numeros separados por coma en lugar de punto. Gracias por tanto Float.parseFloat()
        return Float.parseFloat(valor.replace(",", "."));
    }

    @CircuitBreaker(name="exchange", fallbackMethod = "fallback")
    public Float getExchangeDolar() throws IOException, InterruptedException, ParseException {

        //objeto a enviar a la API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> responseEur = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String valor = JsonParser.parseString(responseEur.body()).getAsJsonArray().get(0).getAsJsonObject().get("casa").getAsJsonObject().get("compra").getAsString();

        return Float.parseFloat(valor.replace(",", "."));
    }

    public Float fallback(final Throwable t) {
       return -1F;
    }
}
