package server;

import java.io.*;
import java.net.*;
import java.util.Set;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Set<ClientHandler> clients;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            writer = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        String message;

        try {
            while ((message = reader.readLine()) != null) {

                System.out.println("Received: " + message);

                // отправка всем
                Server.broadcast(message, this);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}