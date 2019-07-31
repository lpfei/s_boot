package com.example.core.result;

/**
 * Global response entity.
 *
 * @author johnniang
 */
public class BaseResponse<T> extends AbstractBaseResult<T> {

    private BaseResponse() {
    }

    public static BaseResponse fail(String message) {
        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        return response;
    }
}
