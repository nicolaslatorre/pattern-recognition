# pattern-recognition

## Project Description
Given a set of P feature points in the bidimensional plane, determine every line that contains at least N or more COLLINAR points.

A REST API should be exposed that allows the caller to:
- Add a point to the space.
- Get all points in the space.
- Get the longest line segment passing through at least N points.
- Remove all points from the space.

## Implementation Overview
To solve this problem I had used the idea that collinear points which lies on the same line, will have the same slope. 
This means that I can group points by slope and add a line to the space. Once all lines are computed, I can return 
all lines that contains at least N or more collinear points. The complexity of this approach is O(n<sup>2</sup>)
	
### Algorithm
```
For every point P0 in the space
  compute the slope between this point and every other point P in the space.
  Group points by slope and create a Line
  If it is a new Line, add it to the space
return the lines that have at least N or more collinear points.
```

## API
### POST /point with body { "x:": ..., "y:"... }
Add a point to the space. If the point is inserted in the space, the HTTP Status CREATED will be returned.
Coordinates x and y cannot be null, otherwise the HTTP Status BAD_REQUEST will be returned.
The same point cannot be added twice to the space,  otherwise HTTP Status BAD_REQUEST will be returned.

### GET /space
Get all points in the space.
	
### GET /lines/{n}
Get the longest line segment passing through at last N points. The parameter n should be greater than or equal to 2, 
otherwise a HTTP Status BAD_REQUEST will be returned.
	
### DELETE /space
Remove all points from the space.
	
## Usage
### From IDE
Import the project inside your IDE from the following repository:

```https://github.com/nicolaslatorre/pattern-recognition.git```

Run the PatternRecognitionApplication in src/main/java/programming_task/pattern_recognition/PatternRecognitionApplication.java
	
Now you can direct your requests to the address http://localhost:8080/ respecting the provided API.

### Build an executable jar file
This is a Maven project, therefore maven must be installed.

From the terminal go the root folder of the project and execute the following command:

```mvn clean package```
	
This will create a jar inside the target folder named pattern-recognition.jar. 
You can run the application using the following command from the root folder of the project:

```java -jar target/pattern-recognition.jar```

## Improvements
- The testing of the Rest Controller could be improved. Currently there are only two tests, 
one of which is ignored because it produces a nested exception. 
I have to learn how to test extensively a Rest Controller.
	
- A database could be used to store the points in the space. When the application is started, points could be added 
to the space automatically or with a request from the user.
	
- The response body and response status when we try to compute the collinear points could be improved.
Currently when there are no points in the space or there is only one point, I return the status OK and an empty set. 
However we could change the response status to a HTTP Status BAD_REQUEST and notify the user that there should be at 
least two points in the space in order to use the GET /lines/{2} request.
