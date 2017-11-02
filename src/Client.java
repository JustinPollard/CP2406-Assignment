import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Applet implements Runnable, KeyListener{

    JFrame frame;
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static int playerId;
    int[] x = new int[10];
    int[] y = new int[10];

    boolean left, right, up, down;

    int playerX;
    int playerY;

    LightCycle lightCycle = new LightCycle(100, Color.blue);

    public void init(){
        setSize(600,800);
        addKeyListener(this);
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
        while(true) {
            if (right) {
                lightCycle.yDir = -1;
                lightCycle.xDir = 0;
                //playerX += 10;
            }
            if (left == true) {
                lightCycle.yDir = -1;
                lightCycle.xDir = 0;
                //playerX -= 10;
            }
            if (down == true){
                lightCycle.yDir = -1;
                lightCycle.xDir = 0;
                //playerY -=10;
            }
            if (up == true){
                lightCycle.yDir = -1;
                lightCycle.xDir = 0;
                //playerY +=10;
            }
            if(right||left||up||down){
                try {
                    out.writeInt(playerId);
                    out.writeInt(lightCycle.xDir);
                    out.writeInt(lightCycle.yDir);
                }
                //on my one, hav it so the writeInt() happens on every if statement instead of the block
                catch(Exception e){
                    System.out.println("Failed to update coordinates");
                }
            }

            try{
                Thread.sleep(400);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }



    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            up = true;
        }
        //press 'S' to go down
        if (e.getKeyCode() == 83) {
            down = true;
        }
        //press 'A' to go left
        if (e.getKeyCode() == 65) {
            left = true;
        }
        //press 'D' to go right
        if (e.getKeyCode() == 68) {
            right = true;
        }
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            up = false;
        }
        //press 'S' to go down
        if (e.getKeyCode() == 83) {
            down = false;
        }
        //press 'A' to go left
        if (e.getKeyCode() == 65) {
            left = false;
        }
        //press 'D' to go right
        if (e.getKeyCode() == 68) {
            right = false;
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