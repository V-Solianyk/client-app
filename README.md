<h1 style="font-size: 42px;">TCP/IP Client Application</h1>

# Summary
The client program interacts with the server and receives processed commands from the server, as well as logs interaction data to a file.

# Functionalities
- Sending Commands
  - Send commands to the server for processing.

- Receiving Server Messages
  - Receives and displays messages from the server.

- Log incoming and outgoing messages in "LogFile".

# Project structure
- src/main/java: contains all the source code for the application.
- logs/LogFile.log - is a file for logging client-server interaction.
- checkstyle/checkstyle.xml - is a configuration file for the checkstyle tool, which is used to check the code style. It contains settings for various checkstyle modules that perform various code checks for compliance with style standards.
- pom.xml - used to configure and create a Maven project, add the necessary dependencies.
- resources - contains the log4j.xml - file for logger configuration.

# How to run the application
To start the application, it is enough to get the source code of the project and the main thing is to run the server application first and then connect to it to the client. For testing the functionality, the client can send any string or number, or enter the keyword "command1...command10" where the server will send a corresponding message to each of these commands (command1 or command2...command10). The list of these commands and their values is indicated on the server side.
