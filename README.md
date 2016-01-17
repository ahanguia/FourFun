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
