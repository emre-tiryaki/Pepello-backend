package org.pepello.common.exception.handler;

import org.pepello.common.exception.base.PepelloException;
import org.pepello.common.response.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PepelloException.class)
    public ResponseEntity<ApiErrorResponse> handle(PepelloException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ApiErrorResponse(
                        ex.getErrorCode(),
                        ex.getMessage()
                ));
    }
}
