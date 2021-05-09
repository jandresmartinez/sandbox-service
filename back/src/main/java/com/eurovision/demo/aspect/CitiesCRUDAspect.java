package com.eurovision.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
@Slf4j
public class CitiesCRUDAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(getMapping)")
     public void controllerGet(GetMapping getMapping) {
        // Only for pointcut
    }

    @Before("controllerGet(getMapping)")
    public void auditController(JoinPoint joinPoint, GetMapping getMapping) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Executing controller before: " + methodName);
    }


    @AfterReturning(pointcut = "controllerGet(getMapping)", returning = "result")
    public void auditControllerResponse(JoinPoint joinPoint, GetMapping getMapping, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Executing controller after: " + methodName);
    }

    @Before("execution(* com.eurovision.demo.service.CitiesService.*(..))")
    public void logBeforeV1(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        log.info("Executing method: " + methodName);
    }
}
