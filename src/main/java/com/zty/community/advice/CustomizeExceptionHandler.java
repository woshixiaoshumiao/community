package com.zty.community.advice;

import com.zty.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.advice
 * @Author: zhangty
 * @CreateTime: 2022-10-03  22:53
 * @Description: TODO
 * @Version: 1.0
 */
@ControllerAdvice(basePackages = "com.zty.community.controller")
public class CustomizeExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "服务冒烟了，之后再试");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
