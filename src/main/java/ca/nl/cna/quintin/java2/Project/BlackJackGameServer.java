package ca.nl.cna.quintin.java2.Project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The BlackJackGameServer class initializes the server for the blackjack game.
 * It listens for client connections and starts a new thread to handle each client.
 */
public class BlackJackGameServer {
    private static final int PORT = 1234;

    /**
     * The main method starts the server, listens for client connections,
     * and creates a new thread for each client.
     *
     * @throws IOException if an I/O error occurs when opening the socket.
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("BlackJack game server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new BlackJackClientHandler(clientSocket)).start();
            }
        }
    }
}
