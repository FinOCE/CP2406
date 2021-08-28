# [Quiz on chapter 4](https://math.hws.edu/javanotes/c4/quiz.html)

## Question 1

*A "black box" has an interface and an implementation. Explain what is meant by the terms interface and implementation.*

An interface is what represents the functions of the black box to be used externally. In the case of a class "black box" the interface indicates what methods and variables are available. The implementation is the opposite, where it is the actual code involved in handling the external usage in a meaningful way.

## Question 2

*A subroutine is said to have a contract. What is meant by the contract of a subroutine? When you want to use a subroutine, why is it important to understand its contract? The contract has both "syntactic" and "semantic" aspects. What is the syntactic aspect? What is the semantic aspect?*

The "contract" of a subroutine refers to the various expectations when calling/using it. This can include the type of the value returned, the parameters provided and their corresponding types, and the actual name of the subroutine. It is important to understand this contract because it cannot function if the contract is not fulfilled correctly. As an example, if a parameter of type `boolean` is used, but a `int` was provided, the compiler would give an error as this is not a valid fulfilment of the contract. While this explains the syntactic aspect, there is also a semantic aspect, that being the result of the subroutine will likely be unexpected.

## Question 3

*Briefly explain how subroutines can be useful in the top-down design of programs.*

Subroutines are useful for top-down design as they allow the programmer to progressively add new aspects of the code as they are needed. They can start with their `main()` and start creating subroutines as needed in the main routine.

## Question 4

*Discuss the concept of parameters. What are parameters for? What is the difference between formal parameters and actual parameters?*

Parameters are used to provide data to a subroutine to either change how it is handled or make some form of modification of the data. A formal parameters refer to the variables set in the subroutine, typically with a meaningful name. Actual parameters are the values that are being used when calling the subroutine and are represented by the formal parameters within the subroutine.

## Question 5

*Give two different reasons for using named constants (declared with the `final` modifier).*

By creating a named constant, the value does not need to be hard-coded into the program resulting in many duplicates. This means if the constant for whatever reason needed to change, it could be easily done just by changing the `final` declaration. Another reason why using `final` is useful is that it prevents the variable from changing, as constants cannot be modified.

## Question 6

*What is an API? Give an example.*

An API (Application Programming Interface) is a single source that provides a variety of subroutines and functionalities. An example of an API can be a web API, which handles HTTP requests from various endpoints within a shared root and is commonly used to access external data.

## Question 7

*What might the following expression mean in a program?*

```java
(a,b) -> a*a + b*b + 1
```

This code expresses a lambda function that returns the two formal parameters squared plus one. 

## Question 8

*Suppose that `SupplyInt` is a functional interface that defines the method `public int get()`. Write a lambda expression of type `SupplyInt` that gets a random integer in the range 1 to 6 inclusive. Write another lambda expression of type `SupplyInt` that gets an int by asking the user to enter an integer and then returning the user's response.*

`SupplyInt` defines a single method that contains no paramaters and returns an integer.

```java
() -> (int)(Math.random()*6 + 1)
```

```java
// Assuming the TextIO package is used
() -> {
    System.out.print("Enter an integer: ");
    return TextIO.getlnInt();
}
```

## Question 9

*Write a subroutine named "stars" that will output a line of stars to standard output. (A star is the character `*`.) The number of stars should be given as a parameter to the subroutine. Use a for loop. For example, the command `stars(20)` would output:*

```
********************
```

```java
public static void stars(int n) {
    for (int i = 0; i < n; i++) {
        System.out.print("*");
    }

    System.out.println();
}
```

## Question 10

*Write a `main()` routine that uses the subroutine that you wrote for Question 7 to output 10 lines of stars with 1 star in the first line, 2 stars in the second line, and so on, as shown below.*

```
*
**
***
****
*****
******
*******
********
*********
**********
```

```java
public static final int LINE_COUNT = 10;

public static void main(String[] args) {
    for (int i = 1; i <= LINE_COUNT; i++) {
        stars(i);
    }
}
```

## Question 11

*Write a function named `countChars` that has a `String` and a `char` as parameters. The function should count the number of times the character occurs in the string, and it should return the result as the value of the function.*

```java
public static int countChars(String str, char ch) {
    char[] charArray = str.toCharArray();
    int count = 0;

    for (int i = 0; i < charArray.length; i++) {
        if (charArray[i] == ch) count++;
    }

    return count;
}
```

## Question 12

*Write a subroutine with three parameters of type `int`. The subroutine should determine which of its parameters is smallest. The value of the smallest parameter should be returned as the value of the subroutine.*

```java
public static int smallest(int value1, int value2, int value3) {
    if (value1 <= value2 && value1 <= value3) return value1;
    if (value2 <= value1 && value2 <= value3) return value2;
    return value3;
}
```

## Question 13

*Write a function that finds the average of the first N elements of an array of type double. The array and N are parameters to the subroutine.*

```java
public static double average(double[] array, int n) {
    double total = 0;

    for (int i = 0; i < array.length; i++) {
        if (i >= n) break;

        total += array[i];
    }

    return total/n;
}
```

## Question 14

*Explain the purpose of the following function, and explain how it works:*

```java
static int[] stripZeros( int[] list ) {
    int count = 0;
    for (int i = 0; i < list.length; i++) {
        if ( list[i] != 0 )
            count++;
    }
    int[] newList;
    newList = new int[count];
    int j = 0;
    for (int i = 0; i < list.length; i++) {
        if ( list[i] != 0 ) {
            newList[j] = list[i];
            j++;
        }
    }
    return newList;
}
```

The `stripZeros` function starts by counting how many times zero appears in the integer array. It then uses this to create a new array of the same length to the number of zeros. It uses this to then add each number that isn't a zero to the list, incrementing the value `j` to indicate the index of the new array that is next to fill. The new array will be completely filled and will have no zeros in it, which is why it is called `stripZeros`.