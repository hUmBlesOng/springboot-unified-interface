package com.hs.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.springboot.exception.CommonException;
import com.hs.springboot.result.ResponseData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局处理增强版Controller，避免Controller里返回数据每次都要用响应体来包装
 *
 * 实现ResponseBodyAdvice接口
 * 重写supports和beforeBodyWrite方法
 * 避免Controller里返回数据每次都要用响应体来包装
 *
 * @author bys
 * @date 2020/9/24 10:16
 */
@RestControllerAdvice(basePackages = {"com.hs.springboot.controller"}) // 注意哦，这里要加上需要扫描的包
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResponseData那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(ResponseData.class);
    }


    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseData里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResponseData<>(data));
            } catch (JsonProcessingException e) {
                throw new CommonException();
            }
        }
        // 将原本的数据包装在ResponseData里
        return new ResponseData<>(data);
    }
}
