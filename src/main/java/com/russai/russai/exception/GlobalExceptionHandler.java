package com.russai.russai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// This class watches ALL controllers and catches errors before they reach the user
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Fires when someone passes a bad value in the URL — like /api/spirits/banana
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", 400);
        error.put("error", "Bad Request");
        error.put("message", "Invalid ID format. Please provide a valid UUID.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Fires when a URL doesn't match any real endpoint — e.g. a typo, or an
    // endpoint that was removed (like the old backfill-embeddings route).
    // Since Spring Boot 3.2, an unmatched route throws this exception
    // instead of returning a plain 404 directly, which means without this
    // handler it would otherwise fall into the generic catch-all below and
    // look like a server crash instead of a normal "not found."
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoResourceFound(
            NoResourceFoundException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", 404);
        error.put("error", "Not Found");
        error.put("message", "No endpoint exists at this URL.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Fires when a URL's path shape exists but the HTTP verb used doesn't
    // match anything mapped to it — e.g. POSTing to /api/spirits/{id},
    // which only supports GET, PUT, PATCH, and DELETE. This is exactly
    // what happened hitting the old (now-removed) backfill-embeddings URL
    // with POST: the path syntactically matches /api/spirits/{id}, just
    // not for that verb. Without this handler it falls into the generic
    // catch-all below and looks like a server crash instead of a normal
    // "wrong method" response.
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", 405);
        error.put("error", "Method Not Allowed");
        error.put("message", "This URL exists but doesn't support " + ex.getMethod() + ".");

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    // Catches anything else unexpected — the safety net
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", 500);
        error.put("error", "Internal Server Error");
        error.put("message", "Something went wrong. Please try again.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}