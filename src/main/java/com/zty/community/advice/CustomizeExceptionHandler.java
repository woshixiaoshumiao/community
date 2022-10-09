package com.zty.community.advice;

import com.alibaba.fastjson.JSON;
import com.zty.community.dto.ResultDTO;
import com.zty.community.exception.CustomizeErrorCode;
import com.zty.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    @ExceptionHandler(Exception.class)
    public Object handleControllerException(Throwable ex, Model model, HttpServletRequest request,
                                            HttpServletResponse response) {
        String contentType = request.getContentType();
        ResultDTO resultDTO;
        if("application/json".equals(contentType)){
            //返回json
            if(ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try{
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException ioException){

            }
            return null;
        }else{
            //错误页面跳转
            if(ex instanceof CustomizeException){
                model.addAttribute("message", ex.getMessage());
            }else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
