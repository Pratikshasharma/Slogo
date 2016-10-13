# Part 1
(Parser once get strings)
1. My API should be flexible with regards to being able to read in new commands. Also, error checking should be flexible enough to add new constraints/exceptions as needed. Lastly, I should be flexible enough to deal with identifying multiple commands at the same time.

2. My API is very much part of the controller in the implementation of my program (creates usable commands to alter the model). It takes in an input from the view and modifies it so that it can be used as an executable process on the model. 

3. This section will handle a vast majority of the errors. This will include errors handling incorrect number of inputs, incorrect type of inputs, incorrect spelling of an error. This will be presented in command history as an incorrect command (red color maybe) and will show and error alerting the user to the incorrect command.

4. I believe my API is good in being a specific segment that does a crucial part of project. It acts as the controller, so the intermediary between the model and view. It turns inputs from the view into usable code for the model


# Part 2

1. 
User types in 'hi rhondu'
Parser reads in the command, detects no actual command. 
Returns incorrect boolean to view, throws exception that shows error for incorrect command. 

User types in 'fd rhondu'
Parser reads in the command, detects command.
Checks the command with resource file for type of inputs.
Detects incorrect type, returns incorrect boolean to view, throws exception showing error for incorrect command.

User types in 'fd 100'
Parser reads in command, detect command.
Checks the command with resource file for type of inputs
Detects correct type, packages command object with data to execute.
Sends back to simulation, simulation executes on object.
View updates display.

User types in ‘fd 50 fd 50’ which is parsed by the Parser function and first sends ‘fd 50’ to 
Simulation’s CommandCheck instance/class.
CommandCheck class’s StringParse method parses the command into ‘fd’ and ‘50.’
The method CommandCheck checks the command ‘fd’ to make sure it is among the list of commands in the resource file and grabs the number of inputs it has as well as type which checks against inputs given. At this point the method sets a boolean Correct format to true which the front end will access to correctly display command history. 
The command is then sent back to the Simulation which executes the command and then the Display class draws the result.
Repeat for second command

User types in 'fd'
Parser reads in command, detect command.
Checks the command with resource file for type of inputs
Detects absence, adds default distance to command object.
Sends back to simulation, simulation executes on object.
View updates display.

2. Command object interface to account for all the different commands. Also bring in stuff from the resource.

3. Overall functionality of setting up the functionality of commands.

4. Error checking because the instructions are still fairly vague about what instruction format to allow. 