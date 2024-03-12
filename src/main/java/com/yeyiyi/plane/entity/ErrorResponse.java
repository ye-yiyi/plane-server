package com.yeyiyi.plane.entity;

import lombok.Data;

/**
 * @author YeYiYi
 * @date 2024/3/11 14:33
 * @description
 */
@Data
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status,String message){
        this.status = status;
        this.message = message;
    }
}
