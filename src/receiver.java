import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class receiver {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(49152);

        socket.joinGroup(address);
        System.out.println("Connected. Waiting for player...");

        while (true) {
            byte[] messageBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(messageBuffer, messageBuffer.length);

            socket.receive(receivePacket);

            String resultStr = new String(messageBuffer);

            if (resultStr.equals("add"))
                break;

            System.out.println("recieved: " + resultStr);
        }

        socket.leaveGroup(address);
        socket.close();
    }
}