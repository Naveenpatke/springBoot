# Java

1. As stated, main( ) is the method called when a Java application begins. Keep
   in mind that Java is case-sensitive. Thus, Main is different from main. It is
   important to understand that the Java compiler will compile classes that do not
   contain a main( ) method. But java has no way to run these classes. So, if you
   had typed Main instead of main, the compiler would still compile your program.
   However, java would report an error because it would be unable to find the
   main( ) method.
```java
public class Application1 {
	public static void main(String[] args) {
		System.out.println("Hello");
	}
}
```
- Application 1 complies normally
```java
public class Application2 {
	public static void Main(String[] args) {
		System.out.println("Hi");
	}
}
```

- Ans : Application 2 produces error
```
    Main method not found in class Application2, please define the main method as:
    public static void main(String[] args)
```
------
2.  System is a predefined class that provides access to the system,
    and out is the output stream that is connected to the console.
---
