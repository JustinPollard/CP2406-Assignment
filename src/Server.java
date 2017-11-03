import java.io.*;
import java.net.*;

public class Server {
    private static final Users[] user = new Users[20];

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Server...");
        ServerSocket serverSocket = new ServerSocket(4824);
        System.out.println("Server started.");
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        while (true) {
            Socket socket = serverSocket.accept();
            for (int i = 3; i < 20; i++) {
                if (user[i] == null) {
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    user[i] = new Users(out, in, user, i);
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
        String name;
        int playerId;
        int playerIdIn;
        int xIn;
        int yIn;

        Users(DataOutputStream out, DataInputStream in, Users[] user, int pid) {
            this.out = out;
            this.in = in;
            this.user = user;
            this.playerId = pid;
        }

        @Override
        public void run() {
            try {
                //name = in.readUTF();
                out.writeInt(playerId);
            } catch (IOException e) {
                System.out.println("Failed to send Player ID.");
                e.printStackTrace();
            }
            while (true) {
                try {
                    playerIdIn = in.readInt();
                    xIn = in.readInt();
                    yIn = in.readInt();
                    for (int i = 3; i < 20; i++) {
                        if (user[i] != null) {
                            user[i].out.writeInt(playerIdIn);
                            user[i].out.writeInt(xIn);
                            user[i].out.writeInt(yIn);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    user[playerId] = null;
                    break;
                }
            }
        }
    }
}

// "Java UDP help: https://www.youtube.com/watch?v=1a3TtPr_yvI
// Java part 3: https://www.youtube.com/watch?v=XKBYcE59y9w
//alternative links: https://www.youtube.com/watch?v=aIaFFPatJjY
//https://www.youtube.com/watch?v=zj6waSlheXk