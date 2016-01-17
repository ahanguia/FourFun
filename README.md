#4fun Readme
In this readme, you will find out how to run the 4fun client and server, as well as read some of the details on how the client-server communication works. Unfortunately, to setup the server and enable communication between devices is relatively cumbersome. This is the main drawback with using this particular kind of server-client solution.

##Running the server
In order to run the server, follow these steps:

1. Open the backend/AP_Server project folder within eclipse.
2. Export the software as a runnable .jar and run it from the command prompt OR simply run it within eclipse.
3. Make sure the port 12753 has been forwarded to the running machine in the local network's router configuration.
4. Make sure the server application is allowed through any anti-virus software or firewalls
5. The software will print a message, confirming that it has been started

If you want to specify how many people to play with, this can be changed by the roomsize variable in the Group.java class.

##Running the client
In order to run the client on a mobile device, follow these steps:

1. Open the project within android studio
2. In the ResourceState.java class, change the ip address to the external ip (whatismyip.com) of the machine which is running the server.
3. Build and run the application on your phone.

When trying to create or join a room, feedback will be printed by the server. If no such feedback is presented, it means the packages has not reached the server. Either the ip address in resourcestate is wrong, or something else is blocking the communication. In general, if all steps have been executed correctly for setting up the server, this will not be a problem. Unfortunately, however, there might be unforseen reasons to why blockage might appear.

##The protocol
In this section, we will briefly describe how packets work and how they are sent and received. It is a quite shallow description, however.

The networking of 4fun works by sending datagram packages with the UDP transport protocol. Each datagram package contains a bytestream which corresponds to a text string. This text string, in turn, contains a package identifier and additional information, such as answers, room names and questions. An example of such a string could be:

          24:who are you?:Simon:A panda:Bilbo Baggins

24 is the package identifier. Package 24 means that the discussion phase should start, and it contains the question and all the answers as well. Each package type has its own class, which either turns variables into bytestreams or decodes bytestreams into variables. In this case, a string and an arraylist of strings are converted. The information is converted between three different forms:

          Sending:    Variables -> Text String -> Bytestream
          Receiving:  Bytestream -> Text String -> Variables

This is how sending and receiving works, step by step:

Sending:
  1. For example, a Package24StartDiscussion is created by providing the required variables in its constructor. In this case, a question and a list of answers.
  2. The newly created object has a .getBytes() method which returns a bytestream representing the text string, which in turn represents the packet identifier and additional variables
  3. This bytestream is sent to the target ip and port.

Receiving:
  1. A new package is received
  2. The package identifier (always the first two characters) is parsed. 
  3. By using a switch-case, the package is converted and sent to the method which corresponds to the identifier. 
  4. For example, package identifier 24 means the package is converted into a Package24StartDiscussion object. this class's constructor converts the bytestream into usable variables. 
  5. The Package24StartDiscussion object is sent to the handleStartDiscussion method, where the appropriate actions are being executed.
  6. If the received package is supposed to result in an immediate response, such a response is sent. An example of an immediate response is, on the server, when a room name is already taken, or if a room is full.
