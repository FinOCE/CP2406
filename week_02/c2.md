# [Quiz on chapter 2](https://math.hws.edu/javanotes/c2/quiz.html)

## Question 1

*Briefly explain what is meant by the syntax and the semantics of a programming language. Give an example to illustrate the difference between a syntax error and a semantics error.*

The syntax of a programming language is the specific vocabulary used in a language so it can be correctly understood by the machine. This relates to things such as how a loop is written. Semantics is the actual concept of how the code is written to achieve the expected result. Semantics could be considered the use of correct syntax to do something with code.

A syntax error can be something like `System,out.println("this will cause an error")` because it is using a comma instead of a dot, and does not have a semi-colon at the end of the line.

A semantics error could be something like `int total = int1 - int2;` where the intent is to add the variables, but instead subtraction is done, meaning the end result will not be as initially intended.

## Question 2

*What does the computer do when it executes a variable declaration statement. Give an example.*

To handle a variable declaration statement, the computer sets aside some memory to store the data of the variable and associate the name of the variable to that memory. An example of this is `int number = 10;` where the integer `10` is stored in memory and associated to the name `number`.

## Question 3

*What is a type, as this term relates to programming?*

A type is the form in which the data will take. Java has primitive types such as `char` and `int`, and determine how the machine will handle the data stored in memory. Memory only stores binary data, meaning when the program is run, it needs to know what form that binary will take.

## Question 4

*One of the primitive types in Java is boolean. What is the boolean type? Where are boolean values used? What are its possible values?*

Boolean is used to represent true or false. It is used typically in expressions to determine how the code will be executed, like in `if` statements, where it will determine what code block will run.

## Question 5

*Give the meaning of each of the following Java operators:*

### ++

`++` is used on a variable to add 1 to it after being called.

```java
int number = 0;
System.out.println(number++); // returns 0
System.out.println(number); // returns 1
```

### &&

`&&` is the AND operator and is used to test whether multiple expressions are true.

```java
boolean a = true;
boolean b = false;

if (a && b) {
    System.out.println("Both true");
} else {
    System.out.println("One or both are false");
}

// returns "One or both are false" because not both variables are true
```

### !=

`!=` is the NOT EQUAL operator and is used to test is the two expressions are not the same.

```java
if (true != false) {
    System.out.println("not equal");
} else {
    System.out.println("equal");
}

// returns "not equal" because true and false are not the same.
```

## Question 6

*Explain what is meant by an assignment statement, and give an example. What are assignment statements used for?*

An assignment statement is what is used to set a value to a variable, using the EQUALS operator. For example:

```java
int number = 5; // assignment statement sets the variable "number" to equal 5
```

## Question 7

*What is meant by precedence of operators?*

Precedence of operators determines the order the operators will be handled. It follows the mathematical BIDMAS (brackets, integers, division, multiplication, addition, subtraction) and is what allows operators to be handled in an order other than just left-to-right.

## Question 8

*What is a literal?*

A literal is a constant that is hard-coded into the program. These are variables that are predetermined and are not calculated or input in any way.An example of this could be:

```java
int numericLiteral = 10;
String stringLiteral = "text";
```

## Question 9

*In Java, classes have two fundamentally different purposes. What are they?*

Classes have two different purposes. The first one is to group variables and routines into relevant containers. The second one is to represent an object. This can mean it takes the form of a type, such as `String` which can have variables assigned to it which can then use the routines on it. This is a stateful class, whereas the static methods of the class are stateless.

## Question 10

*What is the difference between the statement `x = TextIO.getDouble();` and the statement `x = TextIO.getlnDouble();`*

The method `.getDouble()` takes an input from the user inline, and the pointer of the terminal continues from where the input is submitted. However, `.getlnDouble()` would move the pointer to a new line after receiving the input. It would look as follows:

```java
double x = TextIO.getDouble(); // user inputs "inline"
System.out.println("Text");
double x = TextIO.getlnDouble(); // user inputs "new line"
System.out.println("Text");

/*
Results:
inlineText
new line
Text
*/
```

## Question 11

*Explain why the value of the expression `2 + 3 + "test"` is the string `"5test"`while the value of the expression`"test" + 2 + 3`is the string`"test23"`. What is the value of `"test" + 2 * 3`?*

The expression `2 + 3 + "test"` assumes the type of the expression to be an integer until it reaches the string, whever it is then concatenated to `5test`. In the case of `"test" + 2 + 3` it assumes to type to be string initially, and therefore adds the 2 and 3 as strings, resulting in `test23`. For `"test" + 2 * 3` because of the precedence of operators, would result in `test5` because the multiplier operator is handled before the addition.

## Question 12

*Integrated Development Environments such as Eclipse often use syntax coloring, which assigns various colors to the characters in a program to reflect the syntax of the language. A student notices that Eclipse colors the word String differently from int, double, and boolean. The student asks why String should be a different color, since all these words are names of types. What's the answer to the student's question?*

The `String` type is highlighted differently to the other primitive types because it is actually a class. Other classes that are used will have the same colour as `String`, and all of the primitive types like `char`, `int`, `boolean`, etc will share a separate colour.

## Question 13

*What is the purpose of an import directive, such as import `textio.TextIO` or `import java.util.Scanner`?*

`import` is used to import code that is in a separate file location. In the case of the exercises, the file `TextIO.java` was within a directory `textio` which was in the same directory as the exercise files, which meant to import the class the code would be `import textio.TextIO;` which then allows the class to be used by calling `TextIO.subroutineOrVariable`

## Question 14

*Write a complete program that asks the user to enter the number of "widgets" they want to buy and the cost per widget. The program should then output the total cost for all the widgets. Use `System.out.printf` to print the cost, with two digits after the decimal point. You do not need to include any comments in the program.*

```java
import textio.TextIO;

public class WidgetCalculator {
    public static void main(String[] args) {
        System.out.print("Number of widgets: ");
        int numberOfWidgets = TextIO.getlnInt();

        System.out.print("Cost per widget: $");
        double costPerWidget = TextIO.getlnDouble();

        double total = numberOfWidgets * costPerWidget;

        System.out.printf("Total cost: $%1.2f", total);
    }
}
```
