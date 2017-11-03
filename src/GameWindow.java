import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameWindow {
    public void actionPerformed(ActionEvent e) {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Light Cycles");
        Display display = new Display();
        LightCycle lightCycle = new LightCycle(100, Color.blue);
        Timer timer = new Timer(24, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lightCycle.move(display.getSize());
                if (lightCycle.isWallActive()) {
                    ArrayList<Rectangle> rect = lightCycle.getTrail();
                    rect.add(new Rectangle(lightCycle.getX(), lightCycle.getY(), 30,30 ));
                    lightCycle.setTrail(rect);
                }
                lightCycle.move(display.getSize());
                if (!lightCycle.isActive){
                    display.gameOver();
                }

                display.repaint();
            }
        });
        timer.start();
        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                //press 'W' to go up
                if (e.getKeyCode() == 87) {
                    lightCycle.yDir = -1;
                    lightCycle.xDir = 0;


                }
                //press 'S' to go down
                if (e.getKeyCode() == 83) {
                    lightCycle.yDir = 1;
                    lightCycle.xDir = 0;
                }
                //press 'A' to go left
                if (e.getKeyCode() == 65) {
                    lightCycle.yDir = 0;
                    lightCycle.xDir = -1;
                }
                //press 'D' to go right
                if (e.getKeyCode() == 68) {
                    lightCycle.yDir = 0;
                    lightCycle.xDir = 1;
                }
                //spacebar toggling jet wall
                if (e.getKeyCode() == 32) {
                    lightCycle.jetWall();
                }
                //Shift key increases speed
                if (e.getKeyCode() == 16) {
                    lightCycle.speed = lightCycle.speed + 1;
                }
                //'Z' key decreases speed
                if (e.getKeyCode() == 90) {
                    lightCycle.speed = lightCycle.speed - 1;
                }
            }
        });

/*____________________________Application window's sizing permissions/properties___________________*/
        frame.add(display, BorderLayout.CENTER);

        display.setLightCycle(lightCycle);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //    frame.setResizable(false);
    }
}

//Client Backup

/*
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Applet implements Runnable, KeyListener {

    JFrame frame;
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static int playerId;
    int[] x = new int[10];
    int[] y = new int[10];
    Display display = new Display();


    public void init() {
        setSize(600, 800);
        try {
            System.out.println("Connecting");
            InetAddress address = InetAddress.getByName("10.0.0.106");
            Socket socket = new Socket(address, 4824);
            System.out.println("Connection successful.");
            JOptionPane.showMessageDialog(null, "Connection to Server Successful");
            in = new DataInputStream(socket.getInputStream());
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

    public void updateCoordinates(int pid, int x2, int y2) {
        this.x[pid] = x2;
        this.y[pid] = y2;
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < 10; i++) {
            graphics.setColor(Color.RED);
            graphics.fillRect(x[i], y[i], 30, 30);
            graphics.drawRect(x[i], y[i], 30, 30);
        }
    }
    boolean left, right, up, down;
    int playerx;
    int playery;


    public void run() {
        setBackground(new Color(0, 0, 0));

        while (true) {
            if (right) {
                playerx += 5;
            }
            if (left) {
                playerx -= 5;
            }
            if (up) {
                playery -= 5;
            }
            if (down) {
                playery += 5;
            }
            if (right || left || up || down) {
                try {
                    out.writeInt(playerId);
                    out.writeInt(playerx);
                    out.writeInt(playery);
                }
                //on my one, hav it so the writeInt() happens on every if statement instead of the block
                catch (Exception e1) {
                    System.out.println("Failed to update coordinates");
                }
                repaint();
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


public void keyTyped (KeyEvent e){
        }
public void keyReleased (KeyEvent e){
    System.out.println(e.getKeyCode());
    if (e.getKeyCode() == 87) {
//          lightCycle.yDir = -1;
//          lightCycle.xDir = 0;
        up = false;
        //playery -= 5;
    }
    //press 'S' to go down
    if (e.getKeyCode() == 83) {
//                        lightCycle.yDir = 1;
//                        lightCycle.xDir = 0;
        //playery += 5;
        down = false;

    }
    //press 'A' to go left
    if (e.getKeyCode() == 65) {
//                        lightCycle.yDir = 0;
//                        lightCycle.xDir = -1;
        //playerx -= 5;
        left = false;
    }
    //press 'D' to go right
    if (e.getKeyCode() == 68) {
//                        lightCycle.yDir = 0;
//                        lightCycle.xDir = 1;
        //playerx += 5;
        right = false;
    }
}
public void keyPressed (KeyEvent e){
        System.out.println(e.getKeyCode());
        //press 'W' to go up
        if (e.getKeyCode() == 87) {
//          lightCycle.yDir = -1;
//          lightCycle.xDir = 0;
              up = true;
        //playery -= 5;
        }
        //press 'S' to go down
        if (e.getKeyCode() == 83) {
//                        lightCycle.yDir = 1;
//                        lightCycle.xDir = 0;
        //playery += 5;
            down = true;

        }
        //press 'A' to go left
        if (e.getKeyCode() == 65) {
//                        lightCycle.yDir = 0;
//                        lightCycle.xDir = -1;
        //playerx -= 5;
            left = true;
        }
        //press 'D' to go right
        if (e.getKeyCode() == 68) {
//                        lightCycle.yDir = 0;
//                        lightCycle.xDir = 1;
        //playerx += 5;
            right = true;
        }/*
        //spacebar toggling jet wall
        if (e.getKeyCode() == 32) {
        lightCycle.jetWall();
        }
        //Shift key increases speed
        if (e.getKeyCode() == 16) {
        lightCycle.speed = lightCycle.speed + 1;
        }
        //'Z' key decreases speed
        if (e.getKeyCode() == 90) {
        lightCycle.speed = lightCycle.speed - 1;
        }

        if (e.getKeyCode() == 87 || e.getKeyCode() == 83 || e.getKeyCode() == 65 || e.getKeyCode() == 68) {
        try {
        out.writeInt(playerId);
        out.writeInt(lightCycle.xDir);
        out.writeInt(lightCycle.yDir);
        }
        //on my one, hav it so the writeInt() happens on every if statement instead of the block
        catch (Exception e1) {
        System.out.println("Failed to update coordinates");
        }
}
}




/*
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

    public Input(DataInputStream in, Client c) {
        this.in = in;
        this.client = c;
    }

    public void run() {
        while (true) {
            String message;
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
 */