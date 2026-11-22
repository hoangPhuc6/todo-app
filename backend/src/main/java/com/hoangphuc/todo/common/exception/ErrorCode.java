package com.hoangphuc.todo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ERR_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "List with id %s not found"),
    ERR_TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "Task with id %s not found"),
    ERR_EMAIL_EXISTED(HttpStatus.CONFLICT, "Email %s already exists"),
    ERR_CANNOT_MODIFY_DEFAULT_LIST(HttpStatus.BAD_REQUEST, "Cannot modify default list"),
    ERR_VALIDATION(HttpStatus.BAD_REQUEST, "Validation failed"),
    ERR_INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");

    private final HttpStatus status;
    private final String message; // %s sẽ được thay bằng tham số truyền vào
}