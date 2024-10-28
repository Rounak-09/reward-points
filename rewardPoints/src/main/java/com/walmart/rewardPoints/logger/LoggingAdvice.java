package com.walmart.rewardPoints.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.rewardPoints.exception.SystemException;
import com.walmart.rewardPoints.exception.UserException;
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

    @Pointcut(value = "execution(* com.walmart.rewardPoints.controller.*.*(..) )")
    public void myPointcut() {

    }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName();
        ObjectMapper mapper = new ObjectMapper();
        String methodArguments = "";
        try {
            methodArguments = mapper.writeValueAsString(point.getArgs());
            logger.info("method name: " + methodName + "; method args: " + methodArguments);
            Object response = point.proceed();
            logger.info("method name: " + methodName + "; method response: " + mapper.writeValueAsString(response));
            return response;
        } catch (UserException e) {
            logger.error("method name: " + methodName + "; method args: " + methodArguments + "; error response: " + e.getMessage());
            throw e;
        } catch (Throwable e) {
            logger.error("method name: " + methodName + "; method args: " + methodArguments + "; error response: " + e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }
}
