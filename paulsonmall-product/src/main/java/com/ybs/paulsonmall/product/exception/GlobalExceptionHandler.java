package com.ybs.paulsonmall.product.exception;

import com.ybs.common.exception.BizCodeEnume;
import com.ybs.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler
 * 全局异常
 *
 * @author Paulson
 * @date 2020/6/1 0:19
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常[{}], 异常类型[{}]", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            String message = item.getDefaultMessage();
            String filed = item.getField();
            map.put(filed, message);
        });
        return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(), BizCodeEnume.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public R handler(IllegalArgumentException e) {
        log.error("Assert异常[{}], 异常类型[{}]", e.getMessage(), e.getClass());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handler(RuntimeException e) {
        log.error("运行时异常[{}], 异常类型[{}]", e.getMessage(), e.getClass());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public R handler(Throwable throwable) {
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(), BizCodeEnume.VALID_EXCEPTION.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public R handler(Exception e) {
        log.error("全局异常[{}], 异常类型[{}]", e.getMessage(), e.getClass());
        return R.error(e.getMessage());
    }
}