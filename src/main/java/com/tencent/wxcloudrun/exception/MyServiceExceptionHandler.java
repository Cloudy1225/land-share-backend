package com.tencent.wxcloudrun.exception;

import com.tencent.wxcloudrun.config.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.tencent.wxcloudrun.controller"})
public class MyServiceExceptionHandler {
    @ExceptionHandler(MyServiceException.class)
    @ResponseBody
    private Response handleMyServiceException(MyServiceException e) {
        return new Response(e.getCode(), e.getMessage());
    }

}
