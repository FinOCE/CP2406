# [Quiz on chapter 10](https://math.hws.edu/javanotes/c10/quiz.html)

## Question 1

*What is meant by **generic programming** and what is the alternative?*

Generic programming uses generic types to allow classes and subroutines to work with many different types. As an example, rather than creating many classes for `ArrayListString`, `ArrayListInteger`, etc. the single `ArrayList` can use a generic type of `String`, `Integer`, or anything with the syntax `ArrayList<String>`.

## Question 2

*Why can't you make an object of type `LinkedList<int>`? What should you do instead?*

Generic types cannot use primitive types. Because of this `Integer` is used in place of `int`. It is used virtually the same way you would expect `ArrayList<int>` to function.

## Question 3

*What is an **iterator** and why are iterators necessary for generic programming?*

An iterator allows an object to be iterated over, giving some form of order. It allows for loops and so on to access all available data in the object.

## Question 4

*Suppose that integers is a variable of type `Collection<Integer>`. Write a code segment that uses an iterator to compute the sum of all the integer values in the collection. Write a second code segment that does the same thing using a for-each loop.*

```java
// Using iterator
int sum = 0;
Iterator<Integer> iter = integers.iterator();
while (iter.hasNext())
    sum += iter.next();

// Using for-each
int sum = 0;
for (int number : integers)
    sum += number;
```

## Question 5

*Interfaces such as `List`, `Set`, and `Map` define abstract data types. Explain what this means.*

Interfaces represent the shape of objects rather than the actual shape themselves. This is evident in how they write subroutines, including the inputs and outputs, but none of the logic behind the purpose of the method.

## Question 6

*What is the fundamental property that distinguishes **Sets** from other types of **Collections**?*

Sets cannot contain multiple identical keys, which means if a duplicate key is attempted to be added to the set, it is ignored.

## Question 7

*What is the essential difference in functionality between a `TreeMap` and a `HashMap`?*

The `TreeMap` sorts the keys in ascending order to create the underlying tree, whereas `HashMap` is a hash table using hashing algorithms to place the data in memory. This means the `HashMap` does not have order in its keys.

## Question 8

*Explain what is meant by a **hash code**.*

A hash code is an integer that determines where an object is stored in a hash table. 

## Question 9

*Modify the following `Date` class so that it implements the interface `Comparable<Date>`. The ordering on objects of type Date should be the natural, chronological ordering.*

```java
class Date {
    int month;  // Month number in range 1 to 12.
    int day;    // Day number in range 1 to 31.
    int year;   // Year number.

    Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }
}
```

```java
class Date implements Comparable<Date> {
    int month;  // Month number in range 1 to 12.
    int day;    // Day number in range 1 to 31.
    int year;   // Year number.

    Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public int compareTo(Date other) {
        if (year < other.year)
            return -1;
        else if (year > other.year)
            return 1;
        else if (month < other.month)
            return -1;
        else if (month > other.month)
            return 1;
        else if (day < other.day)
            return -1;
        else if (day > other.day)
            return 1;
        else
            return 0;
    }
}
```

## Question 10

*Suppose that `syllabus` is a variable of type `TreeMap<Date, String>`, where `Date` is the class from the preceding exercise. Write a code segment that will write out the value string for every key that is in the month of December, 2018.*

```java
var data = syllabus.subMap(
    new Date(12, 1, 2018),
    new Date(1, 1, 2019)
);

for (var entry : data)
    System.out.printf("December %s: %s\n", entry.getKey(), entry.getValue());
```

## Question 11

*Write a generic class `Stack<T>` that can be used to represent stacks of objects of type `T`. The class should include methods `push()`, `pop()`, and `isEmpty()`. Inside the class, use an `ArrayList` to hold the items on the stack.*

```java
public class Stack<T> {
    private ArrayList<T> data = new ArrayList<T>();

    public void push(T item) {
        data.add(item);
    }

    public T pop() {
        int top = data.size() - 1;
        return data.remove(top);
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }
}
```

## Question 12

*Write a generic method, using a generic type parameter `<T>`, that replaces every occurrence in an `ArrayList<T>` of a specified item with a specified replacement item. The list and the two items are parameters to the method. Both items are of type `T`. Take into account the fact that the item that is being replaced might be null. For a non-null item, use `equals()` to do the comparison.*

```java
public static <T> void replaceAll(ArrayList<T> list, T oldItem, T newItem) {
    if (oldItem == null) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i) == null)
                list.set(i, newItem);
    } else {
        for (int i = 0; i < list.size(); i++)
            if (oldItem.equals(list.get(i)))
                list.set(i, newItem);
    }
}
```

## Question 13

*Suppose that `words` is an array of Strings. Explain what is done by the following code:*

```java
long n = Arrays.stream(words)
    .filter(w -> w != null)
    .map(w -> w.toLowerCase())
    .distinct()
    .count()
```

Firstly, the array of strings is converted into a stream. It then filters the array to only words that are not null. Then it takes these strings and converts them to lower case. It then uses `distinct()` to remove all duplicates of words. Finally, it counts how many unique strings are left over, finishing off with the value of `n` being the number of unique, non-null, case-insensitive words.

## Question 14

*Use the stream API to print all the even integers from 2 to 20. Start with `IntStream.range` and apply a `filter` operation.*

```java
IntStream.range(2, 20)
    .filter(n -> n % 2 == 0)
    .forEach(n -> System.out.print(n + " "));
```

## Question 15

*Write a generic method `countIf(c,t)` with type parameter `<T>`, where the first parameter, `c`, is of type `Collection<T>`, and the second parameter, `p`, is of type `Predicate<T>`. The method should return the number of items in the collection for which the predicate is true. Give two versions, one using a loop and the other using the stream API.*

```java
// Using loop
public static <T> int countIf(Collection<T> c, Predicate<T> t) {
    int count = 0;

    for (var value : c) {
        if (t.test(value))
            count++;
    }

    return count;
}

// Using stream API
public static <T> int countIf(Collection<T> c, Predicate<T> t) {
    return (int)(c.parallelStream().filter(t).count());
}
```