Joy Kim, Ted Marchildon, Pratiksha Sharma, Vincent Zhang

vwz2

# INTRODUCTION
In this assignment, our primary goal to create an IDE with the ability to write sLogo programs. The programs should allow the user to control a character (turtle) through commands on a per expression bases rather than through a complete program. From a design perspective, our goal is for our application to be flexible enough to take in any command (or multiple commands at the same time) and run and display it properly. We also hope to be able to implement other features easily such as storing command history, display settings, or reading in entire files and animating. This design will include an API which will include commands and parameters that beginning programmers can use. The architecture of our design will be separated into front end and back end. The front end will consist of the main GUI the user interacts with and startup. The startup up simply starts running all the code needed to initiate the program. The GUI will include the display that animates the turtle, the command history, and the text field to enter commands. The front end connects to the back end by accessing the simulation that contains the turtle that the user will control. This includes the turtle position and other factors such as direction and visibility. The back end also contains the parser which will be used to isolate and initiate commands once they are passed in from the front end. This parser will also include error checking that makes sure the commands follow the correct syntax.

# DESIGN OVERVIEW


# USER INTERFACE
	
#API DETAILS

#API EXAMPLE CODE


# DESIGN CONSIDERATIONS

# TEAM RESPONSIBILTIES
**Front End: Ted, Pratiksha**
The front end will be responsible for the Main GUI the user interacts with which consist of two parts: the Display Interface (the animation of the turtle, also includes command history) and the Text Field (sends text to back end).
Ted will be in change of animating the turtle and Pratiksha will be in charge of displaying command history and the text field.

**Back End: Joy, Vincent**
The back end will be responsible for the behind-the-scenes data storage and calculations stored into two parts: the Parser (parsing text received, error checking commands, executing commands) and the Turtle (creating the object that holds position, visibility, etc.).
Joy will be in charge of parsing text and error checking commands and Vincent will be in charge of creating the turtle object and executing commands.