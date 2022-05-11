<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
[![codecov](https://codecov.io/gh/cqfn/eo/branch/master/graph/badge.svg)](https://codecov.io/gh/cqfn/eo)

[![Hits-of-Code](https://hitsofcode.com/github/graur/eo-hamcrest?branch=main)](https://hitsofcode.com/github/graur/eo-hamcrest/view?branch=main)
![Lines of code](https://img.shields.io/tokei/lines/github/Graur/eo-hamcrest)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Graur/eo-tests/blob/main/LICENSE.txt)


EO-Hamcrest is a framework for writing matcher objects, allowing you to declaratively define "match" rules. This tutorial shows you how to use ```eo-hamcrest``` for [EOLANG](https://www.eolang.org) testing.


```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > first-test
  assert-that > a
    4.add 4
    "sum of two numbers"
  a.all-of
    a.equal-to 8
    a.less-than 100
```

The ```assert-that``` object is a stylized sentence for making a test assertion.
In this example, the subject of the assertion is the object ```4.add 4``` that is the first method parameter. The second method parameter is an optional string description of the test case (```sum of two numbers```). The third parameter is a matcher for the first parameter, here a matcher that checks one object is equal and less than to another objects. The test passes since all conditions are ```TRUE```.

### Matchers

Hamcrest comes with a library of useful matchers. Here are some of the most important ones:

###### Core

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > core-matchers-test
  assert-that > a
    5.mul 4
    "multiply of two numbers"
  a.is
    a.anything
```

```.anything``` - always matches, useful if you don’t care what the object under test is

```.described-as``` - decorator to adding custom failure description

```.is``` - decorator to improve readability

###### Logical
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > logical-matchers-test
  assert-that > a
    50.sub 10
    "substract one number from another"
  a.all-of
    a.not 
      a.equal-to 5
    a.any-of
      a.less-than 100
      a.greater-than 1
```

```.all-of``` - matches if all matchers match, short circuits (like Java &&)

```.any-of``` - matches if any matchers match, short circuits (like Java ||)

```.not``` - matches if the wrapped matcher doesn’t match and vice versa

###### Object
```.equal-to``` - the subject of the assertion is equal to some object

###### Collections
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > collections-test
  assert-that > a
    * 50 't' "smth"
  a.array
    a.equal-to 50
    a.equal-to 't'
    a.equal-to "smth"
```
```array``` - test an array’s elements against an array of matchers

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > collections-test
  assert-that > a
    * 1 5 'f' 12
  a.has-item
    [i]
      test.
        assert-that > a
          i
        a.less-than 4
```

```.has-item```, ```.has-items``` - test a collection contains elements

###### Number

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > numbers-matchers-test
  assert-that > a
    31.div 3
    "floating point number closed to 10"
  a.any-of
    a.close-to 10
    a.less-than 20
    a.greater-than 355
```

```.close-to``` - test floating point values are close to a given value

```.greater-than```, ```.less-than``` - test ordering

###### Text

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > text-matchers-test
  assert-that > a
    "Vice versa!"
    "text matchers test"
  a.all-of
    a.equal-to-ignoring-case "vice versa!"
    a.starts-with "Vice"
    a.ends-with "versa!"
    a.contains-string "ver"
```

```.equal-to-ignoring-case``` - test string equality ignoring case

```.equal-to-ignoring-white-space``` - test string equality ignoring differences in runs of whitespace

```.contains-string,``` ```.ends-with```, ```.starts-with``` - test string matching


More examples are [here](https://github.com/Graur/eo-hamcrest/tree/main/examples)

## Custom matchers

You can also implement your own matcher by passing parameter to the ```assert-that.test``` object:

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

test. > my-custom-matcher-test
  assert-that > a
    my-object
  my-custom-matcher obj

[obj] > my-custom-matcher
  [x] > match
    eq. > @
      x
      obj    
```

## How to Contribute

Fork repository, make changes, send us a pull request.
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
