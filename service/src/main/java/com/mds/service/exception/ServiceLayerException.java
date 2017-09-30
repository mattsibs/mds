package com.mds.service.exception;

public class ServiceLayerException extends RuntimeException {
    public ServiceLayerException(final String s) {
        super(s);
    }

    public ServiceLayerException(final String s, final Exception e) {
        super(s, e);
    }
}
