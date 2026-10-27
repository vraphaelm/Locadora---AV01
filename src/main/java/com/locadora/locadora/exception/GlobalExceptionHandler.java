package com.locadora.locadora.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> unauthorized(AuthenticationException ex) {
        return ResponseEntity.status(401).body(
                Map.of("erro", "Credenciais inválidas ou usuário inativo"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> fields.putIfAbsent(
                        error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(
                Map.of("erro", "Dados inválidos", "campos", fields));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> invalid(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> invalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                Map.of("erro", "JSON inválido: confira os campos e as datas"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException ex) {
        return ResponseEntity.status(409).body(
                Map.of("erro", "Operação conflita com registros existentes ou vinculados"));
    }
}