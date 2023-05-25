package com.vn.app_service.constant.enums;

public enum ApiStatus {

    SUCCESS("ES200", "Success"),

    INPUT_ERROR("01", "Data invalid"),

    RESOURCE_NOT_FOUND("02", "Resource not found"),

    UNKNOWN("03", "System error"),

    BAD_CREDENTIALS("04", "Error username or password"),

    BAD_REQUEST("05", "Bad request"),

    ACCESS_DENIED("06", "Access denied");

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
