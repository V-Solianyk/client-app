package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 3344;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()))) {
            String messageToServer;
            String messageFromServer;
            while (true) {
                System.out.print("Enter command: ");
                messageToServer = userInput.readLine();
                outToServer.println(messageToServer);
                if (messageToServer.equalsIgnoreCase("exit")) {
                    break;
                }
                messageFromServer = inFromServer.readLine();
                System.out.println("Server response: " + messageFromServer);
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while communicating with the server."
                    + " Error details: " + e.getMessage(), e);
        }
    }
}
