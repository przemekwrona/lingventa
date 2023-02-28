package com.lingventa.openmeteo.aop;

import com.lingventa.openmeteo.log.LogsService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {

    private final LogsService logsService;

    @Pointcut("@annotation(Log)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void logAllMethodCallsAdvice() {
        logsService.logInfoCallServiceToGetStatistic();
    }
}
