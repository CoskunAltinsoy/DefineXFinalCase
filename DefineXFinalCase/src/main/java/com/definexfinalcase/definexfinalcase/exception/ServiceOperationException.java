package com.definexfinalcase.definexfinalcase.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class ServiceOperationException {
    public static class NotFoundException extends BaseException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
