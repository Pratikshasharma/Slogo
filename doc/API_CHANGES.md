# Changes
Our four APIs now are Display, Integration, Parser, Execution.
The front end consists of Display and Integration and the back end consists of Parser and Execution.
We moved the parser to backend to make it easier to work with execution and now we just pull in the input string from front end. 
We also added Integration into front end/communication between the two sides to allow for minimal exposure between two ends while passing necessary data.

## Back End
The main change to the background was adding a CommandStorage class that stored actors, functions and variables that we could pass through execution to allow for storage of changes to variables, functions, and actors. This also made it easier for the front end to access tthe information that htey needed to animate the scene.

Parser no longer separates the commands to be executed separately but makes a complete expression tree of all the commands entered in one run. Also, because the execution portion was taken out of parser, the error checking of math operations are moved from parser to execution. 

Execution was originally planned to have commands in the methods which would be referenced through one general command class. Now the commands are all individual classes which mkes reflection easier through accessing classes rather than methods.


## Front End
One major change in the internal front end api was the addition of the navigationtabs package which consists of classes such as Tools.java, FileTab.java, Help.java,etc.
These classes are all menu objects which are then called in the MainGUI class to add them up to the Scene. 

Another change is the use of functional interface for using lambda expression while passing in the events from the front end to the back end.

