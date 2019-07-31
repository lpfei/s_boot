package com.example.core.result;

import com.example.core.enums.RequestStatus;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Global response entity.
 *
 * @author johnniang
 */
@Data
public class BaseResponse<T> extends AbstractBaseResult<T> {

    private BaseResponse() {
    }

    public static BaseResponse fail(String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(RequestStatus.FAIL.getValue());
        response.setMessage(message);
        return response;
    }
}
