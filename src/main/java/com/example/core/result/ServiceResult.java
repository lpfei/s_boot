package com.example.core.result;

import lombok.Data;

/**
 * description:业务操作响应(service 层)
 * Created by lpfei on 2019/7/30
 */
@Data
public class ServiceResult extends BaseResponse {
    /**
     * 操作是否成功
     */
    private boolean isSuccess;
}
