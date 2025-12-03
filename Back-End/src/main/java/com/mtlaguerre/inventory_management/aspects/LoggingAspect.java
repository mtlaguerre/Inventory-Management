package com.mtlaguerre.inventory_management.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    /**
     * ASPECT
     *      - define pointcuts, and advice
     *      - join points just exist throughout our application
     */

    /**
     * options for pointcuts:
     *      within() - specify a package or class
     *      execution() - specify a method
     *      bean() - specify a specific bean
     * 
     *      needs entire path defined - basically same as an import statement
     */
    @Pointcut("within(com.mtlaguerre.inventory_management.*.*)")  // all methods in the project package
    public void logAllMethods() {}


    /**
     * TYPES OF ADVICE
     *      Before - advice runs before the method executes
     *      After - advice runs after the method executes
     *      AfterReturning - advice runs after the method executes AND returns something
     *      AfterThrowing - advice runs after the method throws an exception
     *      Around - all of the above
     */
    
    @Before("logAllMethods()")   // tells the advice which pointcut to run on - use method name of pointcut
    public void logBefore(JoinPoint joinPoint) {
        // JoinPoint give information about the join point that is being invoked
        
        // since this runs before the method is invoked, log request info
        log.info("A request was made to [{}] with the argument(s) [{}]",
            joinPoint.getSignature(),
            joinPoint.getArgs()
        );
    }
}
