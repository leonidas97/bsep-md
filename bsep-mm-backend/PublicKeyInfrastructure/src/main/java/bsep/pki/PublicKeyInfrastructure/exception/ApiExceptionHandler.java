package bsep.pki.PublicKeyInfrastructure.exception;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<JsonNode> handleException(ApiException ex) {
        return new ResponseEntity<>(ex.getValidJson(), ex.getStatus());
    }

}
