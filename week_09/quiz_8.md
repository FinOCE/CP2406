# [Quiz on chapter 8](https://math.hws.edu/javanotes/c8/quiz.html)

## Question 1

*Why do programming languages require that variables be declared before they are used? What does this have to do with correctness and robustness?*

Variables need to be declared before they are used because otherwise they would have no meaning. In order to ensure code is written and executed properly, variables need to be defined with their type.

## Question 2

*What is a **precondition**? Give an example.*

A precondition is a condition that is expected to already be true. In the case of `string1.matches(string2)`, it assumes that both `string1` and `string2` are both of type `String`.

## Question 3

*Explain how preconditions can be used as an aid in writing correct programs.*

Preconditions can be useful for writing correct programs because they remove to potential for unexpected results to cause an error. If a program requested data from an online API and the server was offline, it would not receive the expected data. In this case, the program needs to be able to handle if the precondition that the data is correctly sent/received.

## Question 4

*Find a useful loop invariant for the while loop in the binary search algorithm.*

A useful loop invariant for the while loop is if the value being searched is in the array, it will be at one of the indices. Because of this, the search done on the sorted array can be conducted knowing that if the value is found, it exists, and if the value is not found, the value would be between the two last indices.

## Question 5

*Java has a predefined class called `Throwable`. What does this class represent? Why does it exist?*

`Throwable` represents a class that can be thrown with a `throw` statement. Classes like `Exception` are subclasses of `Throwable` and having the class exposed as such allows for custom classes to extend it and create new throwable classes.

## Question 6

*Write a method that prints out a 3N+1 sequence starting from a given integer, `N`. The starting value should be a parameter to the method. If the parameter is less than or equal to zero, throw an `IllegalArgumentException`. If the number in the sequence becomes too large to be represented as a value of type `int`, throw an `ArithmeticException`.*

```java
public static void threeNPlusOne(int value) throws IllegalArgumentException, ArithmeticException {
    if (value <= 0)
        throw new IllegalArgumentException("Given value must be at least 1");

    System.out.printf("Starting value: %d\n", value);
    
    while (value > 1) {
        if (value % 2 == 1) {
            // Odd value
            if (value > 2147483646/3) // Method of checking for max int given in answer
                throw new ArithmeticException("Sequence has grown past the maximum int");

            value = 3 * value + 1;
        } else {
            // Even value
            value /= 2;
        }

        System.out.println(value);
    }
}
```

## Question 7

*Rewrite the method from the previous question, using assert statements instead of exceptions to check for errors. What is the difference between the two versions of the method when the program is run?*

```java
public static void threeNPlusOne(int value) throws IllegalArgumentException, ArithmeticException {
    assert value > 0 : "Given value must be at least 1";

    System.out.printf("Starting value: %d\n", value);
    
    while (value > 1) {
        if (value % 2 == 1) {
            // Odd value
            assert value <= 2147483646/3 : "Sequence has grown past the maximum int" // Method of checking for max int given in answer

            value = 3 * value + 1;
        } else {
            // Even value
            value /= 2;
        }

        System.out.println(value);
    }
}
```

Using assert methods means errors are only checked during debugging and testing. Because of this, if as an example the value grew to past the maximum size of an `int`, no error would be produced and it would attempt to continue to run.

## Question 8

*Some classes of exceptions are checked exceptions that require mandatory exception handling. Explain what this means.*

When an exception requires mandatory exception handling, it means the call needs to be wrapped in a `try..catch` block and handle the given exceptions. Some exceptions can be ignored, but when they are mandatory this error catching is required.

## Question 9

*Consider a subroutine `processData()` that has the header*

```java
static void processData() throws IOException
```

*Write a **try..catch** statement that calls this subroutine and prints an error message if an `IOException` occurs.*

```java
try {
    processData();
} catch (IOException e) {
    System.out.println("There was an IO error");
}
```

## Question 10

*Why should a subroutine throw an exception when it encounters an error? Why not just terminate the program?*

Throwing an exception means that the program is more resilient and can deal with errors on the fly. There are some cases where throwing an error is common like choosing a file to upload, in which case there needs to be handling because a program crashing when an invalid file is chosen is not resilient.

## Question 11

*Suppose that you have a choice of two algorithms that perform the same task. One has average-case run time that is **Θ(n²)** while the run time of the second algorithm has an average-case run time that is **Θ(n * log(n))**. Suppose that you need to process an input of size `n = 100`. Which algorithm would you choose? Can you be certain that you are choosing the fastest algorithm for the input that you intend to process?*

The algorithm with the time complexity of **Θ(n * log(n))** is vastly better than **Θ(n²)**. In the case of an operation with a size of 100, the first of the two calculates out to about 664 (because CS defaults to log base 2 not base 10). However, the second one results in 10000. Because the second algorithm uses **Θ(n²)**, it will always be greater than algorithm 1, regardless of the size of `n`.

## Question 12

*Analyze the run time of the following algorithm. That is, find a function `f(n)` such that the run time of the algorithm is **O(f(n))** or, better, **Θ(f(n))**. Assume that `A` is an array of integers, and use the length of the array as the input size, `n`.*

```java
int total = 0;

for (int i = 0; i < A.length; i++) {
    if (A[i] > 0)
        total = total + A[i];
}
```

The run time of this algorithm is **Θ(n)**. This is clear because the `for` loop iterates over every item in the array `A` from `0` to `A.length-1` with the `i++` increment. This means the loop will run an equal amount of times as there are values in `A`.