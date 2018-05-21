# test-driven-jee-modernisation
An example of how ArchUnit can be used to help you eradicate dependencies to the Java Enterprise framework.

## Original JEE Source

The JEE source code I've used for this exercise came from [https://github.com/apache/tomee/tree/master/examples/ejb-examples](https://github.com/apache/tomee/tree/master/examples/ejb-examples)
I've added the odd extra class to round out the sample. The JEE code is not intended to run.

## Running the samples

**Remember: you should get nothing but failures!** 

```bash
$ mvn clean test
```

## What's happening?

The test suite is asserting that your code is not directly dependent on the classes in the Java Enterprise Edition (JEE) framework.
Because the many classes in the `src/` folder *_are_* dependent on JEE, the ArchUnit test suite is failing.

