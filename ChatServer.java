// ChatServer.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * ChatServer is the main server application that listens for incoming client connections,
 * manages connected clients, and broadcasts messages among them.
 */
public class ChatServer {

    private static final int PORT = 12345; // Port number for the server to listen on
    private static List<ClientHandler> clients = new ArrayList<ClientHandler>(); // List to keep track of all connected clients

    public static void main(String[] args) {
        System.out.println("Chat Server Starting...");
        ServerSocket serverSocket = null; // Declare ServerSocket outside try block
        try {
            serverSocket = new ServerSocket(PORT); // Initialize ServerSocket
            System.out.println("Server listening on port " + PORT);

            // Infinite loop to continuously accept new client connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Blocks until a client connects
                System.out.println("New client connected: " + clientSocket);

                // Create a new thread to handle the connected client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler); // Add the client to the list
                new Thread(clientHandler).start(); // Start the client handler thread
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            // Ensure the server socket is closed even if an exception occurs
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing server socket: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Broadcasts a message from one client to all other connected clients.
     * Messages are obfuscated (simple character shift) before sending.
     *
     * @param senderName The name of the client who sent the message.
     * @param message The original message to be broadcast.
     */
    public static void broadcastMessage(String senderName, String message) {
        String formattedMessage = senderName + ": " + message;
        String obfuscatedMessage = simpleObfuscate(formattedMessage); // Use custom obfuscation

        System.out.println("Broadcasting (obfuscated): " + obfuscatedMessage); // Server logs obfuscated message
        for (ClientHandler client : clients) {
            client.sendMessage(obfuscatedMessage);
        }
    }

    /**
     * Sends the current list of online users to all clients.
     * (This method is part of the server, but the console client won't display it visually.)
     */
    public static void sendUserListToAll() {
        StringBuilder userListBuilder = new StringBuilder("USER_LIST:");
        boolean first = true;
        for (ClientHandler client : clients) {
            if (client.getClientName() != null) {
                if (!first) {
                    userListBuilder.append(",");
                }
                userListBuilder.append(client.getClientName());
                first = false;
            }
        }

        String userListMessage = userListBuilder.toString();
        String obfuscatedUserList = simpleObfuscate(userListMessage);

        for (ClientHandler client : clients) {
            client.sendMessage(obfuscatedUserList);
        }
    }


    /**
     * Removes a disconnected client from the list of active clients.
     *
     * @param clientHandler The handler of the client to be removed.
     */
    public static void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Client disconnected. Active clients: " + clients.size());
        sendUserListToAll(); // Update user list when a client leaves
    }

    /**
     * Simple character shifting for obfuscation. NOT real encryption.
     * @param text The text to obfuscate.
     * @return The obfuscated text.
     */
    public static String simpleObfuscate(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + 1); // Shift each character by 1
        }
        return new String(chars);
    }

    /**
     * Simple character shifting for de-obfuscation. NOT real decryption.
     * @param text The obfuscated text.
     * @return The original text.
     */
    public static String simpleDeobfuscate(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - 1); // Shift each character back by 1
        }
        return new String(chars);
    }
}

/**
 * ClientHandler class handles communication with a single client in a separate thread.
 */
class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName; // Added clientName field

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public String getClientName() { // Getter for clientName
        return clientName;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true); // Auto-flush enabled
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // First message from client should be their name
            clientName = in.readLine();
            if (clientName == null || clientName.trim().isEmpty()) {
                clientName = "Guest-" + clientSocket.getPort(); // Assign a default name if not provided
            }
            System.out.println(clientName + " has joined the chat.");
            ChatServer.broadcastMessage("SERVER", clientName + " has joined the chat.");
            ChatServer.sendUserListToAll(); // Send updated user list to everyone

            String clientMessage;
            // Loop to continuously read messages from this client
            while ((clientMessage = in.readLine()) != null) {
                String decodedMessage = ChatServer.simpleDeobfuscate(clientMessage);
                System.out.println("Received from " + clientName + " (decoded): " + decodedMessage);
                ChatServer.broadcastMessage(clientName, decodedMessage); // Broadcast the decoded message
            }
        } catch (IOException e) {
            // Handle client disconnection or other IO errors
            System.out.println(clientName + " disconnected: " + e.getMessage());
        } finally {
            // Clean up resources when client disconnects or an error occurs
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client resources for " + clientName + ": " + e.getMessage());
            }
            ChatServer.removeClient(this); // Remove this client from the server's list
        }
    }

    /**
     * Sends an obfuscated message to this specific client.
     * @param message The obfuscated message string to send.
     */
    public void sendMessage(String message) {
        out.println(message);
    }
}
