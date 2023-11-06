package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientApp {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 3344;
    private static Thread thread = null;
    private static final Logger LOGGER = LogManager.getLogger(ClientApp.class.getName());

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket
                     .getInputStream()))) {
            thread = new Thread(() -> {
                try {
                    String messageFromServer;
                    while (true) {
                        messageFromServer = inFromServer.readLine();
                        if (messageFromServer.startsWith("Counter ")) {
                            LOGGER.info("Technical information: " + messageFromServer);
                        } else {
                            LOGGER.info(messageFromServer);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            String messageToServer;
            while (true) {
                messageToServer = userInput.readLine();
                outToServer.println(messageToServer);
                if (messageToServer.equalsIgnoreCase("exit")) {
                    break;
                }
                LOGGER.info("Message sent to the server: " + messageToServer);
            }
        } catch (IOException e) {
            LOGGER.error("An error occurred while communicating with the server."
                    + " Error details: " + e.getMessage(), e);
        } finally {
            if (thread != null) {
                thread.interrupt();
            }
        }
    }
}
