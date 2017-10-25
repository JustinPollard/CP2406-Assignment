import java.io.*;
import java.net.*;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;
    static Users[] user = new Users[20];
    static DataInputStream in;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Server...");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server started.");
        while (true) {
            socket = serverSocket.accept();
            for (int i = 3; i < 20; i++) {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                if (user[i] == null)
                {
                    user[i] = new Users(out, in, user);
                    Thread thread = new Thread(user[i]);
                    thread.start();
                    break;
                }
                //out.writeUTF("This is a test of Java sockets.");
                //System.out.println("Data has been sent.");
            }
        }
    }

    static class Users implements Runnable {

        DataOutputStream out;
        DataInputStream in;
        Users[] user = new Users[20];

        public Users(DataOutputStream out, DataInputStream in, Users[] user) {
            this.out = out;
            this.in = in;
            this.user = user;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String message = in.readUTF();
                    for (int i = 3; i < 20; i++) {
                        if (user[i] != null) {
                            user[i].out.writeUTF(message);
                        }
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

// "Java UDP help: https://www.youtube.com/watch?v=1a3TtPr_yvI