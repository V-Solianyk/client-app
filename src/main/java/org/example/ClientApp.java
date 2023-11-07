package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientApp {
    private static final Logger LOGGER = LogManager.getLogger(ClientApp.class.getName());
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final String STOP_CLIENT = "exit";
    private static final int SERVER_PORT = 3344;

    public static void main(String[] args) {
        startClientApp();
    }

    private static void startClientApp() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket
                     .getInputStream()))) {
            receiveTechnicalInfoFromServer(inFromServer);
            sendClientMessagesToServer(userInput, outToServer);
        } catch (IOException e) {
            LOGGER.error("An error occurred while communicating with the server."
                    + " Error details: " + e.getMessage(), e);
        }
    }

    private static void receiveTechnicalInfoFromServer(BufferedReader inFromServer) {
        new Thread(() -> {
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
        }).start();
    }

    private static void sendClientMessagesToServer(BufferedReader userInput,
                                                   PrintWriter outToServer) throws IOException {
        String messageToServer;
        while (true) {
            messageToServer = userInput.readLine();
            outToServer.println(messageToServer);
            if (messageToServer.equalsIgnoreCase(STOP_CLIENT)) {
                break;
            }
            LOGGER.info("Message sent to the server: " + messageToServer);
        }
    }
}
