package com.szqz.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {

    @ApiModelProperty(value = "响应状态码",dataType = "int")
    private int code;

    @ApiModelProperty("响应提示信息")
    private String message;
    //Map<String, Object> data = new HashMap<>(); // 数据

    @ApiModelProperty("响应数据")
    private T data;

    //无权访问
    public void denyAccess(String message){
        this.code = ResStatus.NO_PERMISSION;
        this.message = message;
    }

    //操作成功
    public void success(String message){
        this.code = ResStatus.OK;
        this.message = message;
    }

    //客户端操作失败
    public void fail(String message){
        this.code = ResStatus.NO;
        this.message = message;
    }
}
