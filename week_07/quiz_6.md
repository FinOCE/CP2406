# [Quiz on chapter 6](https://math.hws.edu/javanotes/c6/quiz.html)

## Question 1

*Programs written for a graphical user interface have to deal with "events." Explain what is meant by the term event. Give at least two different examples of events, and discuss how a program might respond to those events.*

An event is some form of action that occurs outside of the program itself. This can include events such as a key being pressed, a button being clicked, and so on. Events can be handled by the program to then do something. For example, if the "Save" button were to be pressed, it could trigger a method that saves the contents of a `TextArea` to a file. 

## Question 2

*A central concept in JavaFX is the **scene graph**. What is a scene graph?*

The scene graph is what represents the content to be displayed. When a JavaFX program is created, it needs to set the scene to some kind of `Node` and through this the program can render the window.

## Question 3

*JavaFX has standard classes called `Canvas` and `GraphicsContext`. Explain the purposed of these classes.*

The `Canvas` class is a node that can be used to draw shapes, text, and others onto. It can be used for countless situations such as where the object drawn does not need its own unique events and just simply needs to display something. The `GraphicsContext` class is, as the name implies, the context to the canvas. This can be used to draw shapes and text, as well as changing various properties like font and colour to be used when drawing.

## Question 4

*Suppose that `canvas` is a variable of type `Canvas`. Write a code segment that will draw a green square centered inside a red circle on the canvas, as illustrated.*

```java
// Assuming the Canvas is the named variable "canvas" and that all relevant imports are made
GraphicsContext ctx = canvas.getGraphicsContext2D();
ctx.setFill(Color.RED);
ctx.fillOval(10, 10, 80, 80);
ctx.setPaint(Color.GREEN);
ctx.fillRect(30, 30, 40, 40);
```

## Question 5

*Draw the picture that will be produced by the following `for` loop, where `g` is a variable of type `GraphicsContext`:*

```java
for (int i=10; i <= 210; i = i + 50) {
    for (int j = 10; j <= 210; j = j + 50) {
        g.strokeLine(i,10,j,60);
    }
}
```

When using this code in a Java file the following image is created:

![drawing](https://math.hws.edu/javanotes/c6/crisscross.png)

This image is taken from [here](https://math.hws.edu/javanotes/c6/crisscross.png) since markdown files only allow images from their file location and cannot be embedded.

## Question 6

*Java has a standard class called `MouseEvent`. What is the purpose of this class? What does an object of type `MouseEvent` do?*

The `MouseEvent` class is an object that contains details about a given mouse event. It allows the event handled to pass on information about the event, such as the coordinates, so more use can come from it in countless ways.

## Question 7

*Explain the difference between a `KeyPressed` event and a `KeyTyped` event.*

The `KeyPressed` event is triggered whenever a key is pressed, regardless of other keys currently pressed or the current state of the application. `KeyTyped` on the other hand only triggers when a character is entered.

## Question 8

*Explain how an `AnimationTimer` is used to do animation.*

The `AnimationTimer` is a class that handles animations. It uses a timer to delay drawing the animation to match a frame-rate so that the animations can be at a consistent speed regardless of the performance of the device it is running on. Every time a new frame is handled, it draws the new frame to the application.

## Question 9

*What is a `CheckBox` and how is it used? How does it differ from a `RadioButton`?*

`CheckBox` is a type of input where it can be either on or off. It basically represents a boolean from an input and can be used particularly for Yes/No situations. `RadioButton` is used for a selection where only one can be selected. It works in the same way as a checkbox where it can be either on or off, but in this case only one button in the selection can be on. If a different button is pressed, the currently on button is turned off.

## Question 10

*What is meant by `layout` of GUI components? Explain briefly how layout is done in JavaFX.*

Layout is the position and size of the GUI components in the application. In JavaFX, layout is done using vaarious classes such as `HBox` and `VBox`, `TilePane` and `BorderPane`, and so on. Each of these components works in its own unique way, but the general purpose is to position elements in appropriate positions for the GUI.

## Question 11

*How does a `BorderPane` arrange the components that it contains?*

A `BorderPane` is used to display up to 5 components. These components can be located in the centre, top, bottom, left, and right, using their respective methods. The corners of the layout are taken by the top and bottom components.

## Question 12

*How is the preferred size of a component set, and how is it used?*

The preferred size of a component is the size that the component should ideally take. It is possible for the component to not be of this size, however it means that in an ideal situation, it will take the given shape. JavaFX components have methods to set the preferred size in the X and Y axes, such as `setPrefWidth`.