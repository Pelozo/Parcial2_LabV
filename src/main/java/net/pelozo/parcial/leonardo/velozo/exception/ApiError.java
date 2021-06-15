package net.pelozo.parcial.leonardo.velozo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;


@AllArgsConstructor
@Data
public class ApiError extends Throwable {
    private HttpStatus Httpstatus;
    private String message;
    private List<String> errors;
}
