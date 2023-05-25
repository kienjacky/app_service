package com.vn.app_service.constant.enums;

public enum ApiStatus {

    SUCCESS("200", "Success"),

    BAD_CREDENTIALS("04", "Error username or password"),
    BAD_REQUEST("400", "BAD_REQUEST"),

    ACCESS_DENIED("202", "Access denied"),

    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR"),

    UNAUTHORIZED("401", "UNAUTHORIZED"),

    FORBIDDEN("403", "UNAUTHORIZED"),

    NOT_FOUND("404", "NOT_FOUND");

    private final String code;

    private final String message;

    ApiStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
