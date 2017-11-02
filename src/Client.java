import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Applet implements Runnable{

    JFrame frame;
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static int playerId;
    int[] x = new int[10];
    int[] y = new int[10];


    public void init(){
        setSize(600,800);
        try {
            System.out.println("Connecting");
            socket = new Socket("localhost", 7777);
            System.out.println("Connection successful.");
            JOptionPane.showMessageDialog(frame,
                    "Connection successful");
            in = new DataInputStream(socket.getInputStream());
            playerId = in.readInt();
            out = new DataOutputStream(socket.getOutputStream());
            Input input = new Input(in, this);
            Thread thread = new Thread(input);
            thread.start();
            Thread thread2 = new Thread(this);
            thread2.start();
        }
        catch (Exception e){
            System.out.println("Unable to start client");
        }
    }

    public void updateCoordinates(int pid, int x2, int y2){
        this.x[pid] = x2;
        this.y[pid] = y2;
    }
public void paint(Graphics graphics){
        for(int i=0;i<10;i++){
            graphics.setColor(Color.RED);
            graphics.drawRect(x[i] - 2, y[i] - 2, 30, 30);
            graphics.fillRect(x[i] - 2, y[i] - 2, 30, 30);
        }
}


    public void run(){
        while(true){

            try{
                Thread.sleep(400);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class Input implements Runnable {
    DataInputStream in;
    Client client;
    public Input(DataInputStream in, Client c){
        this.in = in;
        this.client = c;
    }
    public void run(){
        while (true){
            String message;
            try {
                int playerId = in.readInt();
                int x = in.readInt();
                int y = in.readInt();
                client.updateCoordinates(playerId, x, y);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}