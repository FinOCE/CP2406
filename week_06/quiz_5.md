# [Quiz on chapter 5](https://math.hws.edu/javanotes/c5/quiz.html)

## Question 1

*Object-oriented programming uses **classes** and **objects**. What are classes and what are objects? What is the relationship between classes and objects?*

An object represents a range of properties and methods that reoresents something. A class is used to create these objects, as the objects are instances of the class. An example of this is *Fin* is an object, which is an instance of the `Person` class.

## Question 2

*Explain carefully what **null** means in Java, and why this special value is necessary.*

`null` is a type that means there is no information about that property or thing. This is necessary as not all things are known and have to be assigned at a later point. As an example, when a variable is created with `int number;` it has a value of null as the variable doesn't point to any value in memory, since none has been set.

## Question 3

*What is a **constructor**? What is the purpose of a constructor in a class?*

A constructor is used to create an instance of a class. When the `new` class is created, it runs the constructor and will return with the object created.

## Question 4

*Suppose that `Kumquat` is the name of a class and that `fruit` is a variable of type `Kumquat`. What is the meaning of the statement `fruit = new Kumquat();`? That is, what does the computer do when it executes this statement? (Try to give a complete answer. The computer does several things.)*

When `fruit = new Kumquat();` is run, it assigns an object instance of `Kumquat` to the variable `fruit`. It does this by executing the constructor of the `Kumquat` class as described in the previous question using the `new` operator, and then pointing the variable `fruit` to that location in memory.

## Question 5

*What is meant by the terms **instance variable** and **instance method?***

An *instance variable* is a stateful variable on a class. This means it cannot be used on the static class and is only accessible by an instance of that class. Typically this means the variable will be set to something specific for that instance, rather than the broad class. The same applies to *instance methods*, which are functional methods that can only be used on an instance of the class, rather than the static class itself.

## Question 6

*Explain what is meant by the terms **subclass** and **superclass**.*

A *subclass* is a class that has extended a different class, meaning it inherits properties and methods. A *superclass* is the opposite, as it is the class that has been extended. Generally this means a subclass is a more specific object and a superclass is more general. An example of this is that a `Kumquat` class would be a subclass to the subclass `Fruit`, as there are many types of fruit, and kumquats are a specific type of fruit.

## Question 7

*Modify the following class so that the two instance variables are private and there is a getter method and a setter method for each instance variable:*

```java
public class Player {
    String name;
    int score;
}
```

```java
public class Player {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
```

## Question 8

*Explain why the class `Player` that is defined in the previous question has an instance method named `toString()`, even though no definition of this method appears in the definition of the class.*

All classes in Java extend the base class `Object`, meaning properties/methods from Object are inherited into the `Player` class. `toString()` is an example of a method that is inherited by the class.

## Question 9

*Explain the term **polymorphism.***

*Polymorphism* means that the same properties/methods can mean different things on different classes/objects. This is because they can be individually defined by each class, therefore containing different logic. An example of this is the `toString()` method from the previous question, which is inherited by the superclass `Object`. A new method can be added to `Player` as `toString()` which will then override the original method.

## Question 10

*Java uses **garbage collection** for memory management. Explain what is meant here by garbage collection. What is the alternative to garbage collection?*

*Garbage collection* manages memory by determining what objects and data is no longer needed to run a program. If it is deemed unimportant, it can be cleared from memory, giving space to store new information automatically. If garbage collection is not used, then this is not done automatically, making it easy to have a *memory leak* where the device will run out of memory storing unimportant data since it was never removed.

## Question 11

*What is an **abstract** class, and how can you recognize an abstract class in Java?*

An *abstract class* is a class that is used to represent a thing but that cannot be instantiated itself. This means you cannot use the `new` operator on it, however it can be used to extend a non-abstract class. This means the extended class can still be instantiated with the inherited properties and methods from the abstract class. An abstract class is created in Java by adding the `abstract` modifier to a class definition, for example `abstract public class Person` would be a class that cannot be instantiated, but can be extended by a superclass.

## Question 12

*What is `this`?*

`this` refers to the instance of the object in the given scope. It can be used to call methods or properties from an instance within itself.

## Question 13

*For this problem, you should write a very simple but complete class. The class represents a counter that counts 0, 1, 2, 3, 4, .... The name of the class should be `Counter`. It has one private instance variable representing the value of the `counter`. It has two instance methods: `increment()` adds one to the counter value, and `getValue()` returns the current counter value. Write a complete definition for the class, `Counter`.*

```java
public class Counter {
    private int counter;

    Counter() {
        counter = 0;
    }

    public void increment() {
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
```

## Question 14

*This problem uses the `Counter` class from the previous question. The following program segment is meant to simulate tossing a coin 100 times. It should use two `Counter` objects, `headCount` and `tailCount`, to count the number of heads and the number of tails. Fill in the blanks so that it will do so:*

```java
Counter headCount, tailCount;
tailCount = new Counter();
headCount = new Counter();
for ( int flip = 0;  flip < 100;  flip++ ) {
   if (Math.random() < 0.5)    // There's a 50/50 chance that this is true.
   
       ______________________ ;   // Count a "head".
       
   else
   
       ______________________ ;   // Count a "tail".
}

System.out.println("There were " + ___________________ + " heads.");

System.out.println("There were " + ___________________ + " tails.");
```

```java
Counter headCount, tailCount;
tailCount = new Counter();
headCount = new Counter();

for (int flip = 0; flip < 100; flip++) {
    if (Math.random() < 0.5)
        headCount.increment();
    else
        tailCount.increment();
}

System.out.println("There were " + headCount.getValue() + " heads.");
System.out.println("There were " + tailCount.getValue() + " tails.");
```

## Question 15

*Explain why it can never make sense to test `if (obj.equals(null))`.*

It is impossible for `obj` to have an `equals()` method if it does not exist. `null` refers to something that has no memory address, meaning it has no methods, properties, or anything. Because of this, `equals()` could not exist, therefore meaning the test will always return `true`, provided `obj` is a valid instance of a class with the `equals()` method, and the method works it the way that it can be assumed through its syntax and semantics. If `obj` is indeed `null`, this would cause a `NullPointerException` as you cannot call a nonexistent method on a `null` variable.