# Maven

---
## Maven build life cycle
- validate
- compile
  - compile the source code
  - complie the test code
- Test - where all the unit test cases are executed
- Package [ once all the unit test cases are passed then it goes to package state, where it builds te jar]
- Integration test
- verify
- Install [ Takes the artifacts and install it in local repository] [ .m2 folder ]
- Deploy [ You take the generated artifacts and deploy it in remote maven repository]

---
- mvn clean compile [ It compiles the source code ]
- mvn clean test-compile [ Its complies the sources code then test code ]
- mvn test [ run the test ] [ First validate - compile - test ]
- mvn clean install [ it will execute all the above steps till install ]
- mvn help:effective-settings [ Its list out all the settings used by maven Eg: local/remote repo details ]
- mvn help:effective-pom
- mvn dependency:tree [ To get the dependency tree, how ur getting that dependency ]
- mvn compile --debug [ maven executes in debug mode - Its print lot more content than usually it does ]