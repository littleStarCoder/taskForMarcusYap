package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public record ApiException (String message, Throwable throwable, HttpStatus httpStatus) {
}


