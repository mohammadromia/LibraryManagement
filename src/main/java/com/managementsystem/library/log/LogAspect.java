package com.managementsystem.library.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* com.managementsystem.library.service..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        logger.info("Entering method: {}", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("Exiting method: {}. Execution time: {} ms",
                joinPoint.getSignature().getName(), executionTime);

        return result;
    }


    @AfterReturning(pointcut = "execution(* com.managementsystem.library.service..*(..))", returning = "result")
    public void logOperationSuccess(JoinPoint joinPoint, Object result) {
        logger.info("Operation completed successfully: {}. Result: {}",
                joinPoint.getSignature().getName(), result);
    }

}