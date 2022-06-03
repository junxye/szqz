package com.szqz.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.szqz.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();


//    @ExceptionHandler(value = { ServiceException.class })
    @ResponseBody
    public void serviceExceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        log.error("Service Exception: ", e);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String message = e.getMessage();
//        HttpResult result = new HttpResult(message, null);
        ResultVo result = new ResultVo(000, message, null);

        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(result));

        writer.flush();
        writer.close();
    }

    // https://blog.csdn.net/qq_43409401/article/details/116017177
    // https://blog.csdn.net/zmflying8177/article/details/100755223
    @ExceptionHandler(value = { BindException.class })
    @ResponseBody
    public void bindExceptionHandler(BindException e, HttpServletResponse response) throws IOException {
        log.error("Bind Exception: ", e);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String message = e.getBindingResult().getFieldError().getDefaultMessage();
//        HttpResult result = new HttpResult(message, null);
        ResultVo result = new ResultVo(000, message, null);

        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(result));

        writer.flush();
        writer.close();
    }
}

