# PrimMaze

A basic maze generator using a randomized version of Prim's algorithm, implemented in java.

## Usage
This may be used in another program to generate mazes, or it may be run separately to print a maze of size width by height

After compiling, run with the following: 
```java MazeStructure [width] [height]```
where width and height are integers greater than zero.

If running with the .jar, use ```java -jar PrimMaze[version].jar [width] [height]```

## Sample Output

```
java MazeStructure 15 10
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|                               |                   |       |
+   +   +---+---+   +   +   +---+---+   +---+---+---+---+   +
|   |           |   |   |           |                       |
+---+   +---+   +   +   +   +   +---+   +---+---+   +   +---+
|           |   |   |   |   |                   |   |       |
+   +   +---+---+---+---+---+---+   +---+---+---+---+---+---+
|   |       |                                               |
+---+   +   +---+---+---+---+   +---+---+---+   +---+---+---+
|       |           |       |               |               |
+   +   +---+---+---+   +---+   +---+---+---+---+   +---+---+
|   |   |                               |       |           |
+   +   +   +---+   +---+   +   +---+---+   +---+---+   +---+
|   |   |   |           |   |                       |       |
+   +   +---+   +---+   +   +   +---+---+   +---+---+   +   +
|   |   |           |   |   |           |           |   |   |
+   +---+   +---+---+   +   +---+---+---+---+   +---+---+   +
|   |           |       |                   |           |   |
+   +---+   +---+---+   +   +---+---+---+---+   +---+---+   +
|       |   |           |                   |           |   |
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```
