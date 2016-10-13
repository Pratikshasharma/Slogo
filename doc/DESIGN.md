Joy Kim, Ted Marchildon, Pratiksha Sharma, Vincent Zhang

vwz2

# INTRODUCTION
In this assignment, our primary goal to create an IDE with the ability to write sLogo programs. The programs should allow the user to control a character (turtle) through commands on a per expression bases rather than through a complete program. From a design perspective, our goal is for our application to be flexible enough to take in any command (or multiple commands at the same time) and run and display it properly. We also hope to be able to implement other features easily such as storing command history, display settings, or reading in entire files and animating. This design will include an API which will include commands and parameters that beginning programmers can use. The architecture of our design will be separated into front end and back end. The front end will consist of the main GUI the user interacts with and startup. The startup up simply starts running all the code needed to initiate the program. The GUI will include the display that animates the turtle, the command history, and the text field to enter commands. The front end connects to the back end by accessing the simulation that contains the turtle that the user will control. This includes the turtle position and other factors such as direction and visibility. The back end also contains the parser which will be used to isolate and initiate commands once they are passed in from the front end. This parser will also include error checking that makes sure the commands follow the correct syntax.

# DESIGN OVERVIEW
_The four APIs are the Display Interface, Parser, Command Check, and Simulation._

* The Display Interface has the job of displaying the animation of the turtle (position, path, etc.)  and displaying the command history. The display interface is part of the Main GUI. The Controller will access Simulation in the back end to get actor position and other properties.The command history will hear back from the simulation once error checking is done to be able to display if it is a correct command.
* The Parser has the job of reading in the command from the user. The text field is part of the Main GUI and will pass on the commands to the simulation where it will be parsed. This has the job of taking a input text from the user and separating them out by individual commands. The Parser will also handle loops and conditionals to be able to provide individual commands to simulation.
* The CommandCheck has the job of checking individual command for correct execution and then turning them into executable commands. This is the part of the simulation which will receive the command from the text field via the Main GUI-Simulation link where it will check the command. It will return the results of the check back to the Main GUI for the command history display and then, if correct, set the actors in the simulation to execute the command. 
* The Simulation has the job of executing the commands on the actors as well has holding the actors objects that contain its properties. The actors will receive commands from the simulation (through the parser) and execute the commands. This will change the properties of the actors which the display interface will access through the simulation.


![Image](images/design_overview.jpg)


# USER INTERFACE

The user interface is fairly basic. It will have a screen that will display the turtle’s movements based on the commands entered by the user. Beneath that screen will be a text field where users interact with the application by typing in logo commands. On one side of the screen will be a vertical box that contains the history of commands entered by the user, each of which will be clickable to execute that command again. We will also have a series of buttons or option boxes that will allow the user to choose the background color, the photo for the turtle, the color for the pen, etc. Those are the main modes of user interaction other than typing in commands. We will have some method to alert the user of an incorrect command, whether that is a pop up or red text, it will be something intuitive to alert the user that they are entering an incorrect command.


# API DETAILS

**Display Interface**

This API is an internal API between the MainGUI and the History class. It takes in a String, which is the command entered by the user and displays it in the Command History. This involves maintaining a history of commands entered by the user. A listener will be added to the list of commands and when any command from the history is clicked, it will be displayed in the text field and sent to the back end for parsing and execution. A Console class that holds a Text Field needs to be created so the MainGUI can easily update the Texts in the Text Field held by the Console class. 
A class, CommandPrompt will be created that has a Text Field attribute. The Text Field  attribute will hold the command entered by the user. The command will then be passed into the parser. 
A Turtle class will be created which will hold the position of the turtle and have methods to update the positions. 

**Parser**

This API will handle the commands entered by the user prior to sending them to the CommandCheck API. It will have a class that will receive the raw string from the class that wraps the text field and parse it into individual strings that each contain one command to be sent to the backend. It will also include a class to separate several commands entered at the same time and treat them individually because that is a single process that makes sense to put in its own class. It will also communicate with the backend API by passing the string, so it must contain an instance of CommandCheck to pass to. This can be extended easily by adding additional checks on strings such as formatting a loop entry properly for the backend, and so on. Any string formatting before sending the command to the backend can happen here.

**CommandCheck** 

This API takes the input string from the parser for each individual command  and performs error checking to validate the command. This includes checking that commands have the right number and type of input variables for a command which will be taken from a resource file.  After the commands have been validated, they will be packaged with their components and sent on back to the simulation to have the commands executed. This can be extended by adding more commands to the resource file to check the number of inputs. Furthermore, specific commands will have to have methods added to aid in the checking (ie math operation syntax). Though more classes will be added later, a few general classes that will be needed for this API are the general CommandCheck class which will be in charge of receiving input strings and returning booleans of whether or not the input is correct. This class will also be responsible for sending on working commands to the execution class. There will also be a CommandParsing class within this section to correctly parse out commands and their inputs for the checking process. There will be also a MathParsing class that will deal with the parsing of math related operations. We single out this case because most cases will have math commands themselves be part of another command (ie using sin to calculate a number input for another command) meaning it should be detected within error checking. These are the general sections we expect this API to need with more to come later.

**Simulation**

The Simulation API is responsible for receiving the String command and checking for the validity of the command. It would transform the String into an executable command along with the correct inputs.  The API would execute it on the contained turtle object that will then be able to be accessed by the DisplayInterface to be displayed to the user. The Simulation API holds the CommandChecker, the methods or class of methods to execute the commands, and the Actor object. The Simulation will receive a parsed String command that is sent to the CommandChecker, whose main function is to check for any errors of number of inputs or the type of each input. The class that will execute commands, temporarily called CommandExecuter, will receive the command and correct inputs through CommandChecker and impose these on the actor object. The Actor object itself will contain data of current  position, direction, status of pen, and distance traveled of the actor. The Actor class will take care of error checking if the actor has reached the bounds of the screen dimensions, alerting the user that the command could not be executed or logged as a valid command. The extension of the API will be done through the updating of the resource file that contains the different command-method translations for CommandChecker class as well as creating appropriate methods within the CommandExecuter class. 

# API EXAMPLE CODE

**DisplayHistory Example Code:**
* User types in “fwd 25”. Gets the typed in command and sends it to DisplayHistory() method to add it to the list of entered commands.
* Setting up all the buttons

**Parser Example Code:**
* Receives “fd 60 right 90 pen up fd 30” and will split it into “fd 60” “right 90” “pen up” and “fd 30” and send each command to CommandCheck individually and the display will update after each command also.

**CommandCheck Example Code:**
* User types in ‘fd 50’ which is parsed by the Parser function and first sends ‘fd 50’ to Simulation’s CommandCheck instance/class.
* CommandCheck class’s StringParse method parses the command into ‘fd’ and ‘50.’
* The method CommandCheck checks the command ‘fd’ to make sure it is among the list of commands in the resource file and grabs the number of inputs it has as well as type which checks against inputs given. At this point the method sets a boolean Correctformat to true which the front end will access to correctly display command history. 
* The command is then sent back to the Simulation which executes the command and then the Display class draws the result.

**CommandExecuter Example Code:**
* Receiving code from Simulation
* (This would look like receiving a command string and inputs)
* Using a resource bundle to determine the actual command through the string, the correct command method would be called accordingly. 
* The method would change the Actor object and return the Actor object to Simulation for the UI to grab from.
	
	


# DESIGN CONSIDERATIONS

Parsing User Input Commands: Initially we had decided to send the entire user input command to the back end for parsing. However, this would make parsing multiple commands difficult. Therefore, later it was decided to parse the user input command in the front end, and send in each command separately to check whether or not it was a valid command. For eg, “fd 50 right 30” would be parsed in the front end and “fd 50” and “right 30” would be sent separately to the back end for verification and execution.

One thing to consider is where to store the information on the turtle. On one hand, we could store it in the Display, and then send the command to the backend, and have the backend return some piece of data that we could use to move the turtle. This poses problems in the value returned from the backend. It would need to include data about whether the pen is up or down, the distance, and the new x and y values. Therefore, we decided it made more sense to keep that information in the backend and have the frontend call the data it needs when it needs it, and the backend will manipulate the data based on the commands. 

Another consideration is what to do with the user inputted strings. Currently we are doing some parsing in the frontend to get the strings into a format that the backend can work with, and then the backend interprets the string command. There are several other ways to do this, namely break the string up into characters and integers in the frontend, or pass the whole thing to the backend as a string.

Another design consideration that needs to be further developed is the logging of commands as well as visualizing them. Would this require the logging to solely be the responsibility of frontend or would the data it all be kept in the backend for the UI to access?

Another design element is errors, and where to throw them. Currently we are having the backend determine if the inputted command is not known and throws an error from there, but it could also be done in the front end. However, that would require the front end to have some knowledge about the library of commands, which might involve overlapping the back and front end. There is also the error of the Actor exceeding the bounds of the UI screen, which was discussed to be taken care of in the backend. 

Whether a Controller should be used to communicate between the front end and the back end was another design question the team discussed on. We could go about having the View communicate with the Model, without involving a Controller in between. However, having a Controller class seemed to make the data flow easier, since the data is passed from the front end to the back end every time a new command is entered by the user. 


# TEAM RESPONSIBILTIES
**Front End: Ted, Pratiksha**
The front end will be responsible for the Main GUI the user interacts with which consist of two parts: the Display Interface (the animation of the turtle) and the Text Field (sends text to back end, also includes command history).
Ted will be in change of animating the turtle and Pratiksha will be in charge of displaying command history and the text field.

**Back End: Joy, Vincent**
The back end will be responsible for the simulation which consists of two parts: the Parser (parsing text received, error checking commands) and the Turtle (executing commands, creating the object that holds position, visibility, etc.).
Joy will be in charge of parsing text and error checking commands and Vincent will be in charge of creating the turtle object and executing commands.