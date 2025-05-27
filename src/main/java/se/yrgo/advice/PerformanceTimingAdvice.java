package se.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        // before
        double startTime = System.nanoTime();
        try {
            // proceed to target
            Object value = method.proceed();
            return value;
        } finally {
            // after
            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;
            System.out.println("Time taken for the method " + method.getSignature().getName()+ " from the class \n" +
                               method.getTarget().getClass().getName() + " took "+ (timeTaken  / 1000000) + " ms \n");
        }
    }
}