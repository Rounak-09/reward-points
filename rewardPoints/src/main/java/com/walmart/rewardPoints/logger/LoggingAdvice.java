package com.walmart.rewardpoints.logger;

import com.google.gson.Gson;
import com.walmart.rewardpoints.exception.SystemException;
import com.walmart.rewardpoints.exception.UserException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.walmart.rewardpoints.controller.*.*(..) )")
    public void myPointcut() {

    }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName();
        Gson gson = new Gson();
        String methodArguments = "";
        try {
            methodArguments = gson.toJson(point.getArgs());
            logger.info(String.format("method name: %s; method args: %s", methodName, methodArguments));
            Object response = point.proceed();
            logger.info(String.format("method name: %s; method response: %s", methodName, gson.toJson(response)));
            return response;
        } catch (UserException e) {
            throw e;
        } catch (Throwable e) {
            logger.error(String.format("method name: %s; method args: %s; error response: %s", methodName, methodArguments, e.getMessage()));
            throw new SystemException("Something went wrong, please try again later!");
        }

    }
}
