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
To start the application, it is enough to get the source code of the project and that it is important to run the server first and then connect the client.
