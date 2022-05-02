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

[] > first-test
  assert-that > @
    "sum of two numbers"
    4.add 4
  .equal-to 8
    .or
      .less-than 50
    .and
      .greater-than 6
```

The ```assert-that``` object is a stylized sentence for making a test assertion. 
In this example, this object has two parameters: string description of the test case (```sum of two numbers```) and the subject of the assertion (```4.add 4```).
Then you can see the chain of the nested objects, which are called matchers. So you can apply these matchers to the subject of the assertion to make your test cases more readable.
After running this test, if the assertion is true, it will return: ```TRUE```. Otherwise, you might get the console output with particular exception message.

### Matchers

Hamcrest comes with a library of useful matchers. Here are some of the most important ones:

###### Core

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > core-matchers-test
  assert-that > @
    "multiply of two numbers"
    5.mul 4
  .is
  .anything
  .described-as "this message will never be seen"
```

```.anything``` - always matches, useful if you don’t care what the object under test is

```.described-as``` - decorator to adding custom failure description

```.is``` - decorator to improve readability

###### Logical
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > logical-matchers-test
  assert-that
    "substract one number from another"
    50.sub 10 > assertion
    .all-of > all-tests-must-be-true
      assert-that > @
        ^.assertion
      .any-of
        TRUE
        115.eq 4.add 1
      assert-that > @
        ^.assertion
      .equal-to 40
```

```.all-of``` - matches if all matchers match, short circuits (like Java &&)

```.any-of``` - matches if any matchers match, short circuits (like Java ||)

```.not``` - matches if the wrapped matcher doesn’t match and vice versa


###### Object
```.equal-to``` - the subject of the assertion is equal to some object

```.or``` - matches if either the wrapped matcher or next matcher are TRUE (like Java ||) 

```.and``` - matches if the wrapped matcher and next matcher are both TRUE (like Java &&)

###### Collections
```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > collections-test
  assert-that
    array 1 5 8 12
  .has-item
    [i]
      assert-that > @
        i
      .less-than 4
```
```array``` - test an array’s elements against an array of matchers

```.has-item```, ```.has-items``` - test a collection contains elements

###### Number

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > numbers-matchers-test
  assert-that
    "floating point number closed to 10"
    31.div 3
  .close-to 10
    .or
      .less-than 20
  
```

```.close-to``` - test floating point values are close to a given value

```.greater-than```, ```.less-than``` - test ordering

###### Text

```
+package org.eolang
+alias org.eolang.hamcrest.assert-that
+junit

[] > text-matchers-test
  assert-that
    "text matchers test"
    "Hello World!"
  .equal-to-ignoring-case "hello world!"
    .or
      .starts-with "Hello"
```

```.equal-to-ignoring-case``` - test string equality ignoring case

```.equal-to-ignoring-white-space``` - test string equality ignoring differences in runs of whitespace

```.contains-string,``` ```.ends-with```, ```.starts-with``` - test string matching


More examples are [here](https://github.com/Graur/eo-hamcrest/tree/main/examples)


## How to Contribute

Fork repository, make changes, send us a pull request.
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
