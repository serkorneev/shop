package com.griddynamics.devschool.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Sergey Korneev
 */
@Aspect
public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.griddynamics.devschool.shop..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis() - start;

        Signature signature = joinPoint.getSignature();
        logger.info("{}#{}{}: {}ms", signature.getDeclaringType(),
                signature.getName(), Arrays.toString(joinPoint.getArgs()), end);

        return object;
    }
}
