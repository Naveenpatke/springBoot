package com.learning.springBoot.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJointPointConfig {

    @Pointcut("@annotation(com.learning.springBoot.aspect.TimeTracker)")
    public void trackTimeAnnotation(){}
}
