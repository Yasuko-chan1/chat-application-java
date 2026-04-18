package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final int PORT = 1234;

    // Collections (важно для оценки)
    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {

        System.out.println("Server is starting...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server started on port " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                ClientHandler clientHandler = new ClientHandler(socket, clients);
                clients.add(clientHandler);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // broadcast message to all clients
    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}