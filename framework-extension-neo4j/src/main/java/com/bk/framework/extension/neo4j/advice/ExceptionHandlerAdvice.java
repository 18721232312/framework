package com.bk.framework.extension.neo4j.advice;

import com.bk.framework.extension.neo4j.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BK
 * @version V2.0
 * @description: 异常处理封装
 * @date 2019/9/19 14:18
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResponse errorHandler(Exception ex) {
        log.error("运行时发生异常 ：", ex);
        return ApiResponse.builder().code(ResponseStatus.FAIL_STATUS.getKey()).msg("访问无法正常响应!").build();
    }

    /**
     * 业务异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class, IllegalArgumentException.class})
    public ApiResponse errorHandler(BusinessException ex) {
        return ApiResponse.builder().code(ResponseStatus.FAIL_STATUS.getKey()).msg(ex.getMessage()).build();

    }

}
