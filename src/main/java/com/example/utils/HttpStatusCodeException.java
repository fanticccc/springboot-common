package com.example.utils;

public class HttpStatusCodeException extends RuntimeException {
    private static final long serialVersionUID = -3026015321211967242L;

    private int statusCode;

    private String body;

    public HttpStatusCodeException(int statusCode, String body) {
        super("status code:" + statusCode);
        this.statusCode = statusCode;
        this.body = body;
    }

    public HttpStatusCodeException(int statusCode) {
        this(statusCode, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
