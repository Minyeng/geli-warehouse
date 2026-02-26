package com.geli.warehouse.presentation;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.geli.warehouse.presentation.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ApiResponse<String>> handleRuntime(RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.success(
                                                400,
                                                ex.getMessage(),
                                                null));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<String>> handleValidation(
                        MethodArgumentNotValidException ex) {
                String message = ex.getBindingResult()
                                .getFieldError()
                                .getDefaultMessage();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.success(
                                                400,
                                                message,
                                                null));
        }
}
