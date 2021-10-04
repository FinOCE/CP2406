# [Quiz on chapter 7](https://math.hws.edu/javanotes/c7/quiz.html)

## Question 1

*What is meant by the "basetype" of an array?*

The basetype of an array is the type the contents of the array can take. It is set when the array is initialised and the array can only contain elements of that type.

## Question 2

*What is the purpose of the following variable-arity method? What are the values of `same(1,2,3)`, `same(17,17,17,17)`, and `same(2)`? Why?*

```java
static double same(int... value) {
    for (int i = 1; i < value.length; i++)
        if (value[i-1] != value[i])
            return false;

    return true;
}
```

This method takes an unknown amount of parameters as an input which it creates an int array from. It then iterates over all elements to see if any elements that are identical to the previous value. It will return true if there are no consecutive values or false if any are found.

## Question 3

*What does it mean to sort an array?*

Sorting an array is the process of moving the values of the array around into a meaningful order. Sorting can be done with a variety of algorithms and can take many shapes depending on the use case.

## Question 4

*What is the main advantage of binary search over linear search? What is the main disadvantage?*

Binary search has the potential to be much faster than linear search. Linear search simply makes a linear probe through the array which takes O(n) time. Because binary search is halving the array on each iteration, it only takes O(log n) which scales to be far quicker than linear search. The main disadvantage to binary search is that it requires the array to be sorted. If an array is not sorted, the binary jumps are impossible and the approach would not be feasible.

## Question 5

*What is meant by a dynamic array? What is the advantage of a dynamic array over a regular array?*

A dynamic array is an array that can be of any length. In Java, regular arrays need to have a defined maximum length on initialisation, however dynamic arrays can be resized when required. The advantage of this is that it can be used when there is an unknown or irregular number of values that need to be put into an array.

## Question 6

*What does it mean to say that `ArrayList` is a parameterized type?*

A parameterized type means that the type can take a type as a parameter when setting the type of the variable. This allows for more objects such as `ArrayList` to accept any type of value, whether it be simple primitives or user-defined complicated interfaces. Primitives cannot be used as type paramaters, but they class variants (e.g. `int` to `Integer`) can and allow for `int` values.

## Question 7

*Suppose that a variable strlst has been declared as*

```java
ArrayList<String> strlst = new ArrayList<String>();
```

*Assume that the list is not empty and that all the items in the list are non-null. Write a code segment that will find and print the string in the list that comes first in lexicographic order.*

```java
String first = strlist.get(0);

for (String value : strlist) {
    if (value.compareTo(first) < 0)
        first = value;
}

System.out.printf("The first string in lexographic order is %s", first);
```

This code uses a linear passthrough of the ArrayList and therefore has a time complexity of O(n).

## Question 8

*Show the exact output produced by the following code segment.*

```java
char[][] pic = new char[6][6];
for (int i = 0; i < 6; i++)
    for (int j = 0; j < 6; j++) {
        if (i == j  ||  i == 0  ||  i == 5)
            pic[i][j] = '*';
        else
            pic[i][j] = '.';
    }

for (int i = 0; i < 6; i++) {
    for (int j = 0; j < 6; j++)
        System.out.print(pic[i][j]);
    System.out.println();
}
```

```
******
.*....
..*...
...*..
....*.
******
```

## Question 9

*Write a complete static method that finds the largest value in an array of `int`. The method should have one parameter, which is an array of type `int[]`. The largest number in the array should be returned as the value of the method.*

```java
public static int findLargestValue(int[] array) {
    int largest = array[0];

    for (int value : array) {
        if (value > largest)
            largest = value;
    }

    return largest;
}
```

This method is basically identical to the one used in question 7, and therefore also takes O(n). If the array is sorted before being passed through it could use a binary search to make it faster but since that isn't specified a linear passthrough is the best option.

## Question 10

*Suppose that temperature measurements were made on each day of 2018 in each of 100 cities. The measurements have been stored in an array*

```java
int[][] temps = new int[100][365];
```

where `temps[c][d]` holds the measurement for city number `c` on the `d`th day of the year. Write a code segment that will print out the average temperature, over the course of the whole year, for each city. The average temperature for a city can be obtained by adding up all 365 measurements for that city and dividing the answer by `365.0`.

```java
for (int[] city : temps) {
    int total = 0;

    for (int temp : city) {
        total += temp;
    }

    double average = total/365.0;

    System.out.printf("City %d has an average temperature of %f degrees\n", city, average);
}
```

## Question 11

*Suppose that a class, `Employee`, is defined as follows:*

```java
class Employee {
    String lastName;
    String firstName;
    double hourlyWage;
    int yearsWithCompany;
}
```

*Suppose that data about 100 employees is already stored in an array:*

```java
Employee[] employeeData = new Employee[100];
```

*Write a code segment that will output the first name, last name, and hourly wage of each employee who has been with the company for 20 years or more. Write two versions of the code segment, one that uses a regular for loop, and one that uses a for-each loop.*

```java
// For
for (let i = 0; i < employeeData.length; i++) {
    if (employeeData[i].yearsWithCompany >= 20)
        System.out.printf("%s %s: %f", employeeData[i].firstName, employeeData[i].lastName, employeeData[i].hourlyWage);
}

// For-each
for (Employee employee : employeeData) {
    if (employee.yearsWithCompany >= 20)
        System.out.printf("%s %s: %f", employee.firstName, employee.lastName, employee.hourlyWage);
}
```

## Question 12

*What is the purpose of the following subroutine? What is the meaning of the value that it returns, in terms of the value of its parameter?*

```java
static double[] sums(double[][] data) {
    double[] answers = new double[data.length];

    for (int i = 0; i < data.length; i++) {
        double sum = 0;
        for (int j = 0; j < data[i].length; i++)
            sum = sum + data[i][j];
        answers[i] = sum;
    }

    return answers;
}
```

This subroutine finds the sum of each array stored in the `data` array. `data` is defined as an array of double arrays, and this method returns a double array where each index matches the `data` index where the value is the sum of the values in the `data` double array.