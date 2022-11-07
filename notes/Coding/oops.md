# OOPS

---

There are seven qualities to be satisfied for a programming language to be pure Object Oriented. They are:

- Encapsulation/Data Hiding
- Inheritance
- Polymorphism
- Abstraction
- All predefined types are objects
- All user defined types are objects
- All operations performed on objects must be only through methods exposed at the objects.

Java supports property 1, 2, 3, 4 and 6 but fails to support property 5 and 7 given above. Java language is not a Pure Object Oriented Language as it contain these properties:


- Primitive Data Type ex. int, long, bool, float, char, etc as Objects
- Wrapper Class: Wrapper class provides the mechanism to convert primitive into object and object into primitive. In Java, you can use Integer, Float etc. instead of int, float etc. We can communicate with objects without calling their methods. ex. using arithmetic operators.
- String s1 = "ABC" + "A" ;
- Even using Wrapper classes does not make Java a pure OOP language, as internally it will use the operations like Unboxing and Autoboxing. So if you create Integer instead of int and do any mathematical operation on it, under the hoods Java is going to use primitive type int only.
---
### Polymorphism
There are two types of polymorphism as listed below:

- Static or Compile-time Polymorphism
  - Static or Compile-time Polymorphism when the compiler is able to determine the actual function, it’s called compile-time polymorphism. Compile-time polymorphism can be achieved by method overloading in java. When different functions in a class have the same name but different signatures, it’s called method overloading. A method signature contains the name and method arguments. So, overloaded methods have different arguments. The arguments might differ in the numbers or the type of arguments.

- Dynamic or Run-time Polymorphism
  - Dynamic or Run-time Polymorphism occurs when the compiler is not able to determine whether it’s superclass method or sub-class method it’s called run-time polymorphism. The run-time polymorphism is achieved by method overriding. When the superclass method is overridden in the subclass, it’s called method overriding.
---
### Inheritance

Important facts about inheritance in Java
- Default superclass: Except Object class, which has no superclass, every class has one and only one direct superclass (single inheritance). In the absence of any other explicit superclass, every class is implicitly a subclass of the Object class.
- Superclass can only be one: A superclass can have any number of subclasses. But a subclass can have only one superclass. This is because Java does not support multiple inheritances with classes. Although with interfaces, multiple inheritances are supported by java.
- Inheriting Constructors: A subclass inherits all the members (fields, methods, and nested classes) from its superclass. Constructors are not members, so they are not inherited by subclasses, but the constructor of the superclass can be invoked from the subclass.
- Private member inheritance: A subclass does not inherit the private members of its parent class. However, if the superclass has public or protected methods(like getters and setters) for accessing its private fields, these can also be used by the subclass.