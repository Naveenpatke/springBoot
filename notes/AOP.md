# AOP 

AOP is used to intercept the method call with using annotation using like @Before, @After etc

---
```dtd
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
---
- To mention whether it is a AOP class, we need to annotate the class with @Aspect annotation

### execution(* PACKAGE.*.*(..))
- first star denotes methods with any return type
- PACKAGE denotes in a specific package
- PACKAGE.* denotes any class within the specific package
- *(..) denotes method with irrespective of there package
  
eg:
```java
    @Before("execution(* com.learning.springBoot.controller.*.*(..))")
```
---

- @AfterReturning - is called when a method is executed successfully
```
    @AfterReturning(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()",
    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
        }
```
- @AfterThrowing  - is called when a method is throws a exception
- @After - is called in both the above scenarios
```
    @After(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}
```
---

@Around - intercept a method, allowing it to proceed doing something before it and after it

---
Best practise

- Have a common file for all pointCut definations
```java
    
public class CommonJoinPointConfig {

	@Pointcut("execution(* com.in28minutes.spring.aop.springaop.data.*.*(..))")
	public void dataLayerExecution(){}
	
	@Pointcut("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
	public void businessLayerExecution(){}
	
	@Pointcut("dataLayerExecution() && businessLayerExecution()")
	public void allLayerExecution(){}
	
	@Pointcut("bean(*dao*)")
	public void beanContainingDao(){} // any bean which are defined with this specific name 
	
	@Pointcut("within(com.in28minutes.spring.aop.springaop.data..*)")
	public void dataLayerExecutionWithWithin(){} // is used to intercept all the calls within the specified layer

	@Pointcut("@annotation(com.in28minutes.spring.aop.springaop.aspect.TrackTime)")
	public void trackTimeAnnotation(){}

}
```
- you can even combine multiple join points
```
    @Pointcut("dataLayerExecution() && businessLayerExecution()")
	public void allLayerExecution(){}
```