# test-driven-jee-modernisation
An example of how ArchUnit can be used to help you eradicate dependencies to the Java Enterprise framework.

## Original Source

The EJB source code came from [https://github.com/apache/tomee/tree/master/examples/ejb-examples](https://github.com/apache/tomee/tree/master/examples/ejb-examples)

## Running the samples

**Remember: you should get nothing but failures!** 

```bash
$ mvn clean test
```

## What's happening?

The test suite is asserting that your code is not directly dependent on the classes in the Java Enterprise framework.
Because the classes in the `src/` folder are dependent on JEE, the ArchUnit test suite is failing.