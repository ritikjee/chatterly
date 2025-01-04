package com.chatterly.auth_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.chatterly.auth_service.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ NoHandlerFoundException.class })
    public ResponseEntity<ErrorResponseDTO> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
        ErrorResponseDTO apiErrorResponse = new ErrorResponseDTO(404, "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorResponse);
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest httpServletRequest) {
        ErrorResponseDTO apiErrorResponse = new ErrorResponseDTO(405, "Method not allowed");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorResponse);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest httpServletRequest) {
        ErrorResponseDTO apiErrorResponse = new ErrorResponseDTO(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorResponse);
    }

    @ExceptionHandler({ InternalServerError.class })
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex, HttpServletRequest httpServletRequest) {
        ErrorResponseDTO apiErrorResponse = new ErrorResponseDTO(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorResponse);
    }

}
