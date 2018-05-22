# test-driven-jee-modernisation
An example of how [ArchUnit](https://www.archunit.org) can be used to help you eradicate dependencies to the Java Enterprise framework.

## Original JEE Source

The JEE source code I've used for this exercise came from [https://github.com/apache/tomee/tree/master/examples/ejb-examples](https://github.com/apache/tomee/tree/master/examples/ejb-examples)
I've added the odd extra class to round out the sample. The JEE code is not intended to run.

## Running the samples

**Remember: If you run these tests, you should get _nothing but failures!_** 

```bash
$ mvn clean test
```

## What's happening?

The test suite is asserting that your code is not directly dependent on the classes in the Java Enterprise Edition (JEE) framework.
Because the many classes in the `src/` folder *_are_* dependent on JEE, the ArchUnit test suite is failing.

## What else?

ArchUnit can also be run against exploded JAR's. Unzip your JAR file to get access to the classes and then introduce a LocationProvider into your test suite.


###### MyLocationProvider.java

```java
public class MyLocationProvider implements LocationProvider {

    @Override
    public Set<Location> get(Class<?> testClass) {
        String sources = "target/classes/";
        Set<Location> locations = new HashSet<>();
        locations.add(Location.of(Paths.get(sources)));
        return locations;
    }
}
```

###### ImportTest.java

```java
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(locations = MyLocationProvider.class, importOptions = {ImportOption.DontIncludeTests.class, ImportOption.DontIncludeJars.class})
public class ImportTest {

    @ArchTest
    public static final ArchRule noLocal = ArchRuleDefinition.priority(MEDIUM).noClasses()
            .should().beAnnotatedWith(GET_TYPE.is(nameMatching("javax\\.ejb\\.LocalBean")));
}
```

## Issues

There are some issues I haven't worked through yet...

1. There seems to be some false positives - tests that pass but should actually fail?
1. If an annotation is part of a group of annotations, it doesn't get picked up?