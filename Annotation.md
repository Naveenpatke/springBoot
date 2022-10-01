Annotation
1. Spring Core Annotations [ https://www.baeldung.com/spring-core-annotations ]
1.1. @Autowired [ https://www.baeldung.com/spring-autowire ]
We can use the @Autowired to mark a dependency which Spring is going to resolve and inject. We can use this annotation with a constructor, setter, or field injection.

			@Autowired has a boolean argument called required with a default value of true. It tunes Spring's behavior when it doesn't find a suitable bean to wire. When true, an exception is thrown, otherwise, nothing is wired.

			Note, that if we use constructor injection, all constructor arguments are mandatory.
		
		1.2. @Bean
			@Bean marks a factory method which instantiates a Spring bean:
			
			Note, that all methods annotated with @Bean must be in @Configuration classes.
			
		1.3. @Qualifier
			We use @Qualifier along with @Autowired to provide the bean id or bean name we want to use in ambiguous situations.
		
		1.4. @Required
			@Required on setter methods to mark dependencies that we want to populate through XML
			
		1.5. @Value [ https://www.baeldung.com/spring-value-annotation ]
			We can use @Value for injecting property values into beans. It's compatible with constructor, setter, and field injection.
			
		1.6. @DependsOn
			We can use this annotation to make Spring initialize other beans before the annotated one. Usually, this behavior is automatic, based on the explicit dependencies between beans.
			
		1.7. @Lazy
			We use @Lazy when we want to initialize our bean lazily. By default, Spring creates all singleton beans eagerly at the startup/bootstrapping of the application context.

			However, there are cases when we need to create a bean when we request it, not at application startup.

			This annotation behaves differently depending on where we exactly place it. We can put it on:

				a @Bean annotated bean factory method, to delay the method call (hence the bean creation)
				a @Configuration class and all contained @Bean methods will be affected
				a @Component class, which is not a @Configuration class, this bean will be initialized lazily
				an @Autowired constructor, setter, or field, to load the dependency itself lazily (via proxy)
			
			This annotation has an argument named value with the default value of true. It is useful to override the default behavior.
			
		1.8. @Primary
			Sometimes we need to define multiple beans of the same type. In these cases, the injection will be unsuccessful because Spring has no clue which bean we need.

			We already saw an option to deal with this scenario: marking all the wiring points with @Qualifier and specify the name of the required bean.
			However, most of the time we need a specific bean and rarely the others. We can use @Primary to simplify this case: if we mark the most frequently used bean with @Primary it will be chosen on unqualified injection points:
			
		1.9. @Scope [ https://www.baeldung.com/spring-bean-scopes ]
			We use @Scope to define the scope of a @Component class or a @Bean definition. It can be either singleton, prototype, request, session, globalSession or some custom scope.
			
		1.10. @Profile
			If we want Spring to use a @Component class or a @Bean method only when a specific profile is active, we can mark it with @Profile. We can configure the name of the profile with the value argument of the annotation:	
			
	2. Spring Web Annotations [ https://www.baeldung.com/spring-mvc-annotations ]
		1. @ExceptionHandler
			With this annotation, we can declare a custom error handler method. Spring calls this method when a request handler method throws any of the specified exceptions.

			The caught exception can be passed to the method as an argument:

				@ExceptionHandler(IllegalArgumentException.class)
				void onIllegalArgumentException(IllegalArgumentException exception) {
					// ...
				}
		
		2. @ResponseStatus
			We can specify the desired HTTP status of the response if we annotate a request handler method with this annotation. We can declare the status code with the code argument, or its alias, the value argument.

			Also, we can provide a reason using the reason argument.

			We also can use it along with @ExceptionHandler:

				@ExceptionHandler(IllegalArgumentException.class)
				@ResponseStatus(HttpStatus.BAD_REQUEST)
				void onIllegalArgumentException(IllegalArgumentException exception) {
					// ...
				}
		
		3. @CrossOrigin
	
	3. Spring Boot Annotations [ https://www.baeldung.com/spring-boot-annotations ]
		1.1  @SpringBootApplication
			@SpringBootApplication encapsulates @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
			
		1.2 @EnableAutoConfiguration
			@EnableAutoConfiguration, as its name says, enables auto-configuration. It means that Spring Boot looks for auto-configuration beans on its classpath and automatically applies them.
			
	4. Spring Scheduling Annotations [ https://www.baeldung.com/spring-scheduling-annotations ] Eg : https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-annotations
			When single-threaded execution isn't enough, we can use annotations from the org.springframework.scheduling.annotation package.
		1.1 @EnableAsync
			With this annotation, we can enable asynchronous functionality in Spring.
			We must use it with @Configuration:
			
		1.2 @EnableScheduling
			With this annotation, we can enable scheduling in the application.
			We also have to use it in conjunction with @Configuration:
			
		1.3 @Async [ https://www.baeldung.com/spring-async ]
			We can define methods we want to execute on a different thread, hence run them asynchronously.

			To achieve this, we can annotate the method with @Async:
			If we apply this annotation to a class, then all methods will be called asynchronously.

			Note, that we need to enable the asynchronous calls for this annotation to work, with @EnableAsync or XML configuration.
			
			First, let's go over the rules. @Async has two limitations:

				It must be applied to public methods only.
				Self-invocation — calling the async method from within the same class — won't work.
				
		1.4 @Scheduled [ https://www.baeldung.com/spring-scheduled-tasks ]
			If we need a method to execute periodically, we can use this annotation:
			We can use it to execute a method at fixed intervals, or we can fine-tune it with cron-like expressions.
			
			@Scheduled leverages the Java 8 repeating annotations feature, which means we can mark a method with it multiple times:

				@Scheduled(fixedRate = 10000)
				@Scheduled(cron = "0 * * * * MON-FRI")
				void checkVehicle() {
					// ...
				}
			Note, that the method annotated with @Scheduled should have a void return type.

			Moreover, we have to enable scheduling for this annotation to work for example with @EnableScheduling or XML configuration.
			
			Rules : 
				the method should typically have a void return type (if not, the returned value will be ignored)
				the method should not expect any parameters
				
			Note that scheduled tasks don't run in parallel by default. So even if we used fixedRate, the next task won't be invoked until the previous one is done.
			If we want to support parallel behavior in scheduled tasks, we need to add the @Async annotation:
			
			The fixedDelay property makes sure that there is a delay of n millisecond between the finish time of an execution of a task and the start time of the next execution of the task.
			The fixedRate property runs the scheduled task at every n millisecond. It doesn't check for any previous executions of the task.
			
			@Scheduled(fixedRate = 1000)
			@Scheduled(fixedDelay = 1000)
			@Scheduled(fixedDelay = 1000, initialDelay = 1000)
			@Scheduled(cron = "0 15 10 15 * ?")
			By default, Spring will use the server's local time zone for the cron expression. However, we can use the zone attribute to change this timezone:
			@Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
			
			
			Running Tasks in Parallel [ https://www.baeldung.com/spring-scheduled-tasks#11-running-tasks-in-parallel ] **
				By default, Spring uses a local single-threaded scheduler to run the tasks. As a result, even if we have multiple @Scheduled methods, they each need to wait for the thread to complete executing a previous task.
				
			If our tasks are truly independent, it's more convenient to run them in parallel. For that, we need to provide a TaskScheduler that better suits our needs:

				@Bean
				public TaskScheduler  taskScheduler() {
					ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
					threadPoolTaskScheduler.setPoolSize(5);
					threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
					return threadPoolTaskScheduler;
				}
	5. Spring Data Annotations [ https://www.baeldung.com/spring-data-annotations ]
		5.1. @Transactional [ https://www.baeldung.com/transaction-configuration-with-jpa-and-spring ]
			When we want to configure the transactional behavior of a method, we can do it with:

				@Transactional
				void pay() {}
			If we apply this annotation on class level, then it works on all methods inside the class. However, we can override its effects by applying it to a specific method.
		5.2. @Modifying
			We can modify data with a repository method if we annotate it with @Modifying:

			@Modifying
			@Query("UPDATE Person p SET p.name = :name WHERE p.id = :id")
			void changeName(@Param("id") long id, @Param("name") String name);
	
	6. Spring Bean Annotations [ https://www.baeldung.com/spring-bean-annotations ]
		6.1 Component Scanning
			Spring can automatically scan a package for beans if component scanning is enabled.

			@ComponentScan configures which packages to scan for classes with annotation configuration. We can specify the base package names directly with one of the basePackages
			or value arguments (value is an alias for basePackages):
			
		6.2 @Component
			@Component is a class level annotation. During the component scan, Spring Framework automatically detects classes annotated with @Component:
			
			By default, the bean instances of this class have the same name as the class name with a lowercase initial. In addition, we can specify a different name using the optional value argument of this annotation.

			Since @Repository, @Service, @Configuration, and @Controller are all meta-annotations of @Component, they share the same bean naming behavior. Spring also automatically picks them up during the component scanning process.
		
		6.3 @Repository
		6.4 @Configuration
			Configuration classes can contain bean definition methods annotated with @Bean
		6.5 Stereotype Annotations and AOP [ https://www.baeldung.com/spring-bean-annotations#annotations ]

Spring Bean Scopes [ https://www.baeldung.com/spring-bean-scopes ]