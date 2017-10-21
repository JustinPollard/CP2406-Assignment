import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(49152);

        socket.joinGroup(address);
        System.out.println("Connected. Waiting for player...");
        ArrayList <String> userList = new ArrayList<>();

        while (true) {
            byte[] messageBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(messageBuffer, messageBuffer.length);

            socket.receive(receivePacket);
            String resultStr = new String(messageBuffer).trim();
            System.out.println("received: " + resultStr);

            //check if username is in the userList variable
            //assign userList variable with username and return a greeting to the user
            if (userList.contains(resultStr)) {
                //add resultStr to array
                String usedUserName = "Sorry, this name is taken.";
                DatagramPacket usedName = new DatagramPacket(usedUserName.getBytes(),usedUserName.length(), address, 49152);
                socket.send(usedName);
//                String userName = "Hello" + resultStr;
//
//                socket.send(user);
//                userList = resultStr;
            }
            //if username is taken then send an 'incorrect' statement
            else {

                DatagramPacket user = new DatagramPacket(resultStr.getBytes(), resultStr.length(), address, 49152);
                socket.send(user);
                break;
            }
        }
//following two code snippets don't work
        socket.leaveGroup(address);
        socket.close();
    }
}