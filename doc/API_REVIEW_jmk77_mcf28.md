# Part 1

**What about your API/design is intended to be flexible?**

The CommandCheck API, in order for new user commands to be accounted for. Because the user-defined commands will be formulated from existing commands that are
valid within our Slogo project, this shouldn't require too much changing of the actual code but rather storing the list of commands to be able to be called 
when necessary. The code should be flexible enough to account for this and to still be able to call these new functions that whose command name will be new. 

**How is your API/design encapsulating your implementation decisions?**

By creating the Simulation API, the CommandCheck, CommandExecuter, and Actor decisions would be encapsulated and essentially not be seen above the simulation
level. 

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

The exceptions that might occur is incorrect or invalid input, both command and parameters, format of the input, invalid character used as parameter. 
These exceptions would be thrown with a message to the frontend where frontend would handle the exception in the form of an alert box. 

**Why do you think your API/design is good (also define what your measure of good is)?**

The design is good in that the communication between the frontend and backend relies on the turtle object, not allowing frontend to see how the turtle was 
updated but merely showing the result of the string command it had sent down.



# Part 2

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

* TO custom functions
* changing the state of an actor
* for loop block 
* variable scoping, use of variable
* fd sum x y - dealing with multiple commands and combination of turtle and math/boolean commands.

**How do you think at least one of the "advanced" Java features will help you implement your design?**

Reflection would be able to help in terms of passing from backend to frontend certain JavaFX implementations that cannot be passed through the turtle object 
are wanted that the frontend wasn't necessarily aware of, due to the parsing occuring in the backend. 

**What feature/design problem are you most excited to work on?**

Excited to work on figuring out how exactly to parse the commands. 

**What feature/design problem are you most worried about working on?**

Worried about the quality of communication, are we hiding enough between frontend and backend?




