package com.example.dao.exception;

public class NotFoundSqlException extends RuntimeException {

    public NotFoundSqlException(String msg) {
        super(msg);
    }
}
