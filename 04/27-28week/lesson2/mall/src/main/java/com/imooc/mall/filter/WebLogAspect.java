package com.imooc.mall.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 打印请求合响应信息
 */
@Aspect
@Component
public class WebLogAspect {
        private final Logger log = LoggerFactory.getLogger(WebLogAspect.class);
        @Pointcut("execution(public * com.imooc.mall.controller.*.*(..))")
        public void webLog(){

        }

        @Before("webLog()")
        public  void doBefore(JoinPoint joinPoint){
                // 收到请求记录请求内容
             ServletRequestAttributes   attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
             HttpServletRequest request = attributes.getRequest();
             log.info("URL:"+request.getRequestURL().toString());
             log.info("HTTP_METHOD:"+request.getMethod());
             log.info("IP:"+request.getRemoteAddr());
             log.info(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
             log.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));
        }
        @AfterReturning(returning = "res",pointcut = "webLog()")
        public  void doAfterReturning(Object res){
//                log.info("RESPONSE",+new ObjectMapper().writeValueAsString(res));
        }
}
