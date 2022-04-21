<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
[![codecov](https://codecov.io/gh/cqfn/eo/branch/master/graph/badge.svg)](https://codecov.io/gh/cqfn/eo)

[![Hits-of-Code](https://hitsofcode.com/view/github/Graur/eo-tests?branch=main)](https://hitsofcode.com/view/github/Graur/eo-tests?branch=main)
![Lines of code](https://img.shields.io/tokei/lines/github/Graur/eo-tests)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Graur/eo-tests/blob/main/LICENSE.txt)

[EOLANG](https://www.eolang.org) objects for testing.

This is how you can write your own tests in EO:

```
+package org.eolang
+alias org.eolang.test.assert-equals

[] > arithmetic-add-works
  assert-equals > @
    "two numbers"
    5.add 1
    6
```

Some assertions object for testing:

1) Equals:
```
+package org.eolang
+alias org.eolang.test.assert-equals

[] > arithmetic-add-works
  assert-equals > @
    "two numbers"    #test name
    5.add 1          #actual
    6                #expected
```

output message will be: 
```PASSED``` 
and return: ```TRUE```

2) Not equals:
```
+package org.eolang
+alias org.eolang.test.assert-not-equals

[] > arithmetic-add-works2
  assert-not-equals > @
    "two numbers"    #test name
    5.add 1          #actual
    6                #expected
```

output message will be: 
```
FAILED: expected: <6> not equal to: <6>
```
and return: ```FALSE```

3) Greater than:
```
+package org.eolang
+alias org.eolang.test.assert-gt

[] > greater-than-test
  assert-gt > @
    "one number greater than another one"
    5.add 6
    8
```

output message will be:
```PASSED```
and return: ```TRUE```

4) Less than:
```
+package org.eolang
+alias org.eolang.test.assert-lt

[] > less-than-test
  assert-lt > @
    "one number less than another one"
    5.add 6
    8
```

output message will be:
```FAILED: expected: <11> less than: <8>```
and return: ```FALSE```

5) True:
```
+package org.eolang
+alias org.eolang.test.assert-true

[] > assert-true-test
  assert-true > @
    "number is equal to another number"
    5.eq 5
```

output message will be:
```PASSED```
and return: ```TRUE```

6) False:
```
+package org.eolang
+alias org.eolang.test.assert-false

[] > assert-false-test
  assert-false > @
    "number is not equal to another number"
    5.eq 5
```

output message will be:
```FAILED: expected: <5.eq 5> is false```
and return: ```FALSE```

## How to Contribute

Fork repository, make changes, send us a pull request.
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
