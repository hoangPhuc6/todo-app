package com.hoangphuc.todo.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoangphuc.todo.common.exception.ErrorCode;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL) // field null sẽ không xuất hiện trong JSON
public record ApiResponse<T>(
        boolean success,
        String code,
        String message,
        T data, // dữ liệu trả về khi thành công
        Map<String, String> errors, // chi tiết lỗi validation
        Instant timestamp) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "SUCCESS", "Success", data, null, Instant.now());
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, "SUCCESS", message, data, null, Instant.now());
    }

    public static ApiResponse<Void> error(ErrorCode errorCode, String message, Map<String, String> errors) {
        return new ApiResponse<>(false, errorCode.name(), message, null, errors, Instant.now());
    }
}
