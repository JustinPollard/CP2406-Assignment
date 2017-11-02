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
                if (user[i] == null){
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());
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

        public Users(DataOutputStream out, DataInputStream in, Users[] user, int pid) {
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
                }
                catch (IOException e){
                    e.printStackTrace();
                    user[playerId] = null;
                }
            }
        }
    }
}

// "Java UDP help: https://www.youtube.com/watch?v=1a3TtPr_yvI
// Java part 3: https://www.youtube.com/watch?v=XKBYcE59y9w
//alternative links: https://www.youtube.com/watch?v=aIaFFPatJjY
//https://www.youtube.com/watch?v=zj6waSlheXk