import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.util.Scanner;

public class sender {
    public static void main(String[] args) throws Exception {

        String name;
        System.out.println("Menu: \nadd: add your username \nend: quit game.Enter Choice: ");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();


        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(490);

        socket.joinGroup(address);
        DatagramPacket sendPacket = new DatagramPacket(name.getBytes(), name.length(), address, 49152);

        socket.send(sendPacket);
        socket.leaveGroup(address);
        socket.close();
    }
}
