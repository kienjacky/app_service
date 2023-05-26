package com.vn.app_service.payload.response;

import com.vn.app_service.constant.enums.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {

    @Builder.Default
    private String status = ApiStatus.SUCCESS.getStatus();

    @Builder.Default
    private String message = ApiStatus.SUCCESS.getMessage();

    private T data;

    @Builder.Default
    private long responseTime = System.currentTimeMillis();

    public BaseResponse(ApiStatus apiStatus) {
        responseTime = System.currentTimeMillis();
        this.status = apiStatus.getStatus();
        this.message = apiStatus.getMessage();
    }

    public BaseResponse(T data) {
        responseTime = System.currentTimeMillis();
        this.status = ApiStatus.SUCCESS.getStatus();
        this.message = ApiStatus.SUCCESS.getMessage();
        this.data = data;
    }
}
