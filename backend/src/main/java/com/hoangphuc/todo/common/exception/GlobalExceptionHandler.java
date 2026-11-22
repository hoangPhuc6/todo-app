package com.hoangphuc.todo.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hoangphuc.todo.common.dto.ApiResponse;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleApp(AppException ex) {
        return build(ex.getErrorCode(), ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return build(ErrorCode.ERR_VALIDATION, ErrorCode.ERR_VALIDATION.getMessage(), errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex) {
        log.error("Unexpected error", ex);
        return build(ErrorCode.ERR_INTERNAL, ErrorCode.ERR_INTERNAL.getMessage(), null);
    }

    private ResponseEntity<ApiResponse<Void>> build(ErrorCode code, String message, Map<String, String> errors) {
        return ResponseEntity
                .status(code.getStatus()) // mã HTTP thật: 404, 409, 400...
                .body(ApiResponse.error(code, message, errors));
    }
}