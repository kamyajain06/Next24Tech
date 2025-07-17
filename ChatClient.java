// ChatClient.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ChatClient is the client application that connects to the chat server,
 * sends messages, and receives messages from other clients.
 */
public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost"; // Server IP address (use "localhost" for local testing)
    private static final int SERVER_PORT = 12345; // Server port number

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String userName;
    private Scanner consoleScanner;

    public ChatClient(String userName) {
        this.userName = userName;
        this.consoleScanner = new Scanner(System.in);
    }

    /**
     * Starts the client application, connecting to the server and setting up
     * threads for reading and writing messages.
     */
    public void startClient() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to the chat server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); // Auto-flush enabled

            // Send username to the server immediately upon connection
            out.println(userName);

            // Start a new thread for reading messages from the server
            new Thread(new ReadThread()).start();

            // Main thread handles writing messages to the server
            writeMessages();

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            // Clean up resources when client disconnects or an error occurs
            try {
                if (consoleScanner != null) consoleScanner.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client resources: " + e.getMessage());
            }
        }
    }

    /**
     * Reads user input from the console and sends it to the server.
     * Messages are obfuscated (simple character shift) before sending.
     */
    private void writeMessages() {
        System.out.println("Type your message and press Enter. Type 'quit' to exit.");
        String message;
        while (true) {
            message = consoleScanner.nextLine();
            if (message.equalsIgnoreCase("quit")) {
                break; // Exit the loop if user types 'quit'
            }
            // Obfuscate the message before sending
            String obfuscatedMessage = simpleObfuscate(message); // Use custom obfuscation
            out.println(obfuscatedMessage); // Send obfuscated message to server
        }
    }

    /**
     * ReadThread is a nested class that continuously reads incoming messages from the server.
     */
    private class ReadThread implements Runnable {
        @Override
        public void run() {
            String serverResponse;
            try {
                // Loop to continuously read messages from the server
                while ((serverResponse = in.readLine()) != null) {
                    // De-obfuscate the incoming message before displaying
                    String decodedResponse = simpleDeobfuscate(serverResponse);
                    // The console client simply prints all received messages.
                    // It doesn't parse "USER_LIST:" like the GUI client would.
                    System.out.println(decodedResponse);
                }
            } catch (IOException e) {
                // Handle server disconnection or other IO errors
                System.out.println("Disconnected from server: " + e.getMessage());
            }
        }
    }

    /**
     * Simple character shifting for obfuscation. NOT real encryption.
     * @param text The text to obfuscate.
     * @return The obfuscated text.
     */
    private static String simpleObfuscate(String text) {
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
    private static String simpleDeobfuscate(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - 1); // Shift each character back by 1
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Scanner setupScanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String userName = setupScanner.nextLine();

        ChatClient client = new ChatClient(userName);
        client.startClient(); // Start the client application
        setupScanner.close();
    }
}
