# [Quiz on chapter 3](https://math.hws.edu/javanotes/c3/quiz.html)

## Question 1

*What is an algorithm?*

An algorithm is a process that makes a calculation dependent on the provided variables. It can be used to make mathematical calculations, handle large amounts of data, or countless other situations.

## Question 2

*Explain briefly what is meant by "pseudocode" and how is it useful in the development of algorithms.*

Pseudocode is a development process of designing an algorithm without writing actual syntactically valid code. Rather than writing code to test, pseudocode is instead used to plan the steps of an algorithm beforehand, to then make writing the actual code much easier, since the focus is on the intent of the algorithm rather than the syntax of the language used. It is intended to be human-readable, meaning it is easier to design with, especially if multiple people are involved.

## Question 3

*What is a block statement? How are block statements used in Java programs?*

A block statement is a sequence of code that is contained within curly braces. It is used in a variety of situations and perform various functions, such as organising code into reusable classes and methods, scoping, and containing code from operations such as `if`, `while`, `for`, etc.

## Question 4

*What is the main difference between a `while` loop and a `do..while` loop?**

A `do while` loop starts by running a single iteration, and then checks to see if the expression is valid. It will continue running until the expression is no longer valid. However, the `while` loop will check the expression before the first iteration, meaning it has the potential to never run at all.

## Question 5

*What does it mean to prime a loop?*

Priming a loop is done by preparing all variables and requirements before a loop is run. This can include getting a response from a user, fetching data, declaring variables, etc. The intention of priming a loop is that the loop will be able to run correctly with all data provided beforehand.

## Question 6

*Explain what is meant by an animation and how a computer displays an animation.*

An animation is some form of movement made on the screen. Because computers display frames, an animation is crated by a series of still frames which when stitched together quickly look like movement.

## Question 7

*Write a `for` loop that will print out all the multiples of 3 from 3 to 36, that is: 3 6 9 12 15 18 21 24 27 30 33 36.*

```java
for (int i = 3; i <= 36; i += 3) {
    System.out.print(i + " ");
}
```

## Question 8

*Fill in the following `main()` routine so that it will ask the user to enter an integer, read the user's response, and tell the user whether the number entered is even or odd. (You can use `TextIO.getInt()` to read the integer. Recall that an integer `n` is even if `n % 2 == 0`)*

```java
public static void main(String[] args) {
    System.out.print("Enter a number: ");

    int userInput = TextIO.getInt();
    bool isEven = userInput % 2 == 0;

    System.out.print("Your number is " + (isEven ? "even" : "odd"));
}
```

## Question 9

*Write a code segment that will print out two different random integers selected from the range 1 to 10. All possible outputs should have the same probability. Hint: You can easily select two random numbers, but you have to account for the fact that the two numbers that you pick might be the same.*

```java
int x, y;

do {
    x = (int)(Math.random()*10 + 1)
    y = (int)(Math.random()*10 + 1)
} while (x == y);

System.out.print(x + " " + y);
```

## Question 10

*Suppose that `s1` and `s2` are variables of type `String`, whose values are expected to be string representations of values of type `int`. Write a code segment that will compute and print the integer sum of those values, or will print an error message if the values cannot successfully be converted into integers. (Use a try..catch statement.)*

```java
String s1 = "1";
String s2 = "2";

try {
    System.out.print(Integer.parseInt(s1) + Integer.parseInt(s2));
} catch (Exception e) {
    System.out.print("Error: At least one of the strings was not a representation of an int.");
}
```

## Question 11

*Show the exact output that would be produced by the following `main()` routine:*

```java
public static void main(String[] args) {
    int N;
    N = 1;
    while (N <= 32) {
       N = 2 * N;
       System.out.println(N);   
    }
}
```

The exact output of this would be the powers of two until 64:

```
2
4
8
16
32
64
```

## Question 12

*Show the exact output that would be produced by the following `main()` routine:*

```java
public static void main(String[] args) {
   int x,y;
   x = 5;
   y = 1;
   while (x > 0) {
      x = x - 1;
      y = y * x;
      System.out.println(y);
   }
}
```

The exact output of this would be:

```
4
12
24
24
0
```

## Question 13

*What output is produced by the following program segment? Why? (Recall that `name.charAt(i)` is the i-th character in the string, name.)*

```java
String name;
int i;
boolean startWord;

name = "Richard M. Nixon";
startWord = true;
for (i = 0; i < name.length(); i++) {
   if (startWord)
      System.out.println(name.charAt(i));
   if (name.charAt(i) == ' ')
      startWord = true;
   else
      startWord = false;
}
```

This code is using spaces to determine the start of a word, and printing the following character. Because of this, the output would be

```
R
M
N
```

## Question 14

*Suppose that `numbers` is an array of type `int[]`. Write a code segment that will count and output the number of times that the number 42 occurs in the array.*

```java
int[] numbers = { 42, 10, 12, 32, 42, 10 }; // 42 appears twice

int numberToFind = 42;
int appearances = 0;
for (int i = 0; i < numbers.length; i++) {
    if (numbers[i] == numberToFind) appearances++;
}

System.out.printf("The number %d appeared %d times in the array.", numberToFind, appearances);
```

## Question 15

*Define the range of an array of numbers to be the maximum value in the array minus the minimum value. Suppose that `raceTimes` is an array of type `double[]`. Write a code segment that will find and print the range of raceTimes.*

```java
double[] raceTimes = { 10, 12, 11, 15, 3, 12 }; // min = 3, max = 15

double minValue = raceTimes[0];
double maxValue = raceTimes[0];

for (int i = 0; i < raceTimes.length; i++) {
    if (raceTimes[i] < minValue) minValue = raceTimes[i];
    if (raceTimes[i] > maxValue) maxValue = raceTimes[i];
}

double range = maxValue - minValue;

System.out.print("The range of the array is " + range);
```
