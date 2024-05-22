package com.demo.warehousemanagementsystem.exception;

public class JsonStringConversionException extends RuntimeException {
    public JsonStringConversionException(String message) {
        super(message);
    }

    public JsonStringConversionException(String message, Throwable e) {
        super(message, e);
    }
}
