# Changes
Our four APIs now are Display, Integration, Parser, Execution.
The front end consists of Display and Integration and the back end consists of Parser and Execution.
We moved the parser to backend to make it easier to work with execution and now we just pull in the input string from front end. 
We also added Integration into front end/communication between the two sides to allow for minimal exposure between two ends while passing necessary data.

## Back End
The main change to the background was adding a CommandStorage class that stored actors, functions and variables that we could pass through execution to allow for storage of changes to variables, functions, and actors. This also made it easier for the front end to access the information that they needed to animate the scene.

Parser no longer separates the commands to be executed separately but makes a complete expression tree of all the commands entered in one run. Also, because the execution portion was taken out of parser, the error checking of math operations are moved from parser to execution. 

Execution was originally planned to have commands in the methods which would be referenced through one general command class. Now the commands are all individual classes which makes reflection easier through accessing classes rather than methods. This also allows for the addition of command interfaces/abstract classes to add layers of hierarchy. 

Lastly, saving/loading was implemented in more of an intemediary class rather than in back end due to make it easier to implement button functions. Accessing back end functions and variables weren't a problem due to observable/observer relations implemented which is why we went with this design.


## Front End
One major change in the internal front end API was the addition of the navigationtabs package which consists of classes such as Tools.java, FileTab.java, Help.java,etc.
These classes are all menu objects which are then called in the MainGUI class to add them up to the Scene. The MenuLayout interface has the APIs for the MenuTemplate super class.
Another change is the use of functional interfaces for creating our own lambda expressions that fully encapsulate instance variables that would have 
otherwise needed to be passed around.

The APIs for MainGUI.java class are also a part of the Display APIs. Though the APIs did not change much, there were definitely addition of a couple of more public methods that were needed
as we progressed for the GUIController to have access to some of the front end components. The MainGUITemplate interface houses all the APIs for MainGUI.java that are a part of the Display API.

One another change in Display API is the addition of TurtleManager class whcih has a bunch of public methods to set and get Active turtle. The public methods it houses are also a part of the Integration API.

One change in the Integration API is the creation of ISlogo interface which consists of methods to start the slogo program and return Tab for the active workspace.

The biggest change in the Integration API is the addition of Turtleable interface which consists of methods that allow the front end to interact with the back end and get the reuqired data.





