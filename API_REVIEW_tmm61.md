Part 1
==============

1. I am focusing on the front end, and our API must be flexible in the sense that it should have as limited knowledge
about the backend as possible. To do that we are having the front end design be completely private, and then the only
interaction with the backend is getting the updated positions of the actor after processing. 

2. The design encapsulates the data that is exclusive to the frontend and only retrieves the data it needs from
the backend

3. The errors are going to be detected in the backend and returned to the front end, where the front end will determine
an intuitive way to display the error to the user. 


4. I think it's good because it doesn't rely on the backend, so theoretically with our design we can attach any backend 
and should get the same results. 

Part 2
=================

1. * fd 50: The frontend receives the text as a string in the Main Display class. The string is sent to the Controller
where it goes through the parsing tree before each command is interpreted by the backend. The backend performs the command on 
the data it contains and the front end then gets the data back from the backend and updates the screen

	* error 98: The frontend receives the text as a string in the Main Display class where it is then sent to the controller
	where it goes through the parsing tree. The backend sees this message is an error and instantiates the appropriate
	error message and sends it back to the controller class where it alerts the frontend to pop up an error alert.
	
	* empty string: The frontend receives the text as a string in the MainDisplay class and passes it to the controller where 
	it won't match anything on the tree and returns an error alert.
	
2. I think binding will be useful to update the backend with the new data from the frontend.

3. I'm excited to work on the regex tree, I think that is interesting.

4. I'm worried about integrating the backend with the frontend properly.