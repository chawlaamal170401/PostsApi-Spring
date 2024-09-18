package org.amal.postapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitoringAspect {

  @Around("@annotation(TimeMonitoring)")
  public void calculateTime(ProceedingJoinPoint joinPoint){
    long startTime = System.currentTimeMillis();

    try{
      joinPoint.proceed();
    } catch (Throwable e) {
      System.out.println("Something went wrong");
    } finally {
      long endTime = System.currentTimeMillis();
      long executionTime = endTime - startTime;
      System.out.println("Time taken: " + executionTime + " ms..");
    }

  }

}
