import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {


        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(49152);
        InetAddress localIP = InetAddress.getLocalHost();

        String name;
        System.out.println("Enter Username: ");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        String message = String.format(name, localIP.getHostAddress());


        //send user's input to receiver.java
        socket.joinGroup(address);
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), address, 49152);
        socket.send(sendPacket);

        //output either an accepted username message or a taken/unaccepted username message
        byte[] messageBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(messageBuffer, messageBuffer.length);
        socket.receive(receivePacket);
        String resultStr = new String(messageBuffer).trim();GameWindow.main(new String[0]);
        System.out.println(resultStr);

        socket.leaveGroup(address);
        socket.close();
        JOptionPane.showMessageDialog(null, "Finding a player...");
    }
}
