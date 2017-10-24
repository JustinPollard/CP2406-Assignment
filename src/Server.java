import java.io.*;
import java.net.*;

public class Server{
    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Server...");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server started.");
        socket = serverSocket.accept();
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("This is a test of Java sockets.");
    }
}