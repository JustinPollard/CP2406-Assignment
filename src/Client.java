import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Client extends Applet implements Runnable, KeyListener {

    private static DataOutputStream out;
    private static int playerId;
    private int[] x = new int[10];
    private int[] y = new int[10];

    @Override
    public void init() {
        setSize(600, 800);
        setVisible(true);
        addKeyListener(this);
        try {
            System.out.println("Connecting");
            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address, 4824);
            System.out.println("Connection successful.");
            JOptionPane.showMessageDialog(null, "Connection to Server Successful\n" +
                    "Use the WASD keys to move\nDon't hit the borders or the Jetwalls");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            playerId = in.readInt();
            out = new DataOutputStream(socket.getOutputStream());
            Input input = new Input(in, this);
            Thread thread = new Thread(input);
            thread.start();
            Thread thread2 = new Thread(this);
            thread2.start();
        } catch (Exception e) {
            System.out.println("Unable to start client");
        }
    }

    private void updateCoordinates(int pid, int x2, int y2) {
        this.x[pid] = x2;
        this.y[pid] = y2;
    }

    //Sprite
    @Override
    public void paint(Graphics graphics) {
        for (int i = 0; i < 10; i++) {
            graphics.setColor(Color.RED);
            graphics.fillRect(x[i], y[i], 30, 30);
            graphics.drawRect(x[i], y[i], 30, 30);
        }
    }
    private boolean left, right, up, down;
    private int playerx;
    private int playery;

    //movement and coordinate sending
    @Override
    public void run() {
        setBackground(new Color(0, 0, 0));

        while (true) {
            repaint();
            if (right) {
                playerx += 10;
            }
            if (left) {
                playerx -= 10;
            }
            if (up) {
                playery -= 10;
            }
            if (down) {
                playery += 10;
            }
            if (right || left || up || down) {
                try {
                    out.writeInt(playerId);
                    out.writeInt(playerx);
                    out.writeInt(playery);
                }
                catch (Exception e1) {
                    System.out.println("Failed to update coordinates");
                }

            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Key mapping
@Override
public void keyTyped (KeyEvent e){
        }
@Override
public void keyPressed (KeyEvent e) {
    System.out.println(e.getKeyCode());
        //press 'W' to go up
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
@Override
public void keyReleased (KeyEvent e){
    System.out.println(e.getKeyCode());
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

    class Input implements Runnable {
        DataInputStream in;
        Client client;

        Input(DataInputStream in, Client c) {
            this.in = in;
            this.client = c;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    int playerId = in.readInt();
                    int x = in.readInt();
                    int y = in.readInt();
                    client.updateCoordinates(playerId, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
