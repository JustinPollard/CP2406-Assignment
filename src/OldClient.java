//import javax.swing.*;
//import java.applet.Applet;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class Client extends Applet implements Runnable{
//
//    JFrame frame;
//    static Socket socket;
//    static DataInputStream in;
//    static DataOutputStream out;
//    static int playerId;
//    int[] x = new int[10];
//    int[] y = new int[10];
//
//
//    public void init(){
//        try {
//            System.out.println("Connecting");
//            socket = new Socket("localhost", 7777);
//            System.out.println("Connection successful.");
//            JOptionPane.showMessageDialog(frame,
//                    "Connection successful");
//            in = new DataInputStream(socket.getInputStream());
//            playerId = in.readInt();
//            out = new DataOutputStream(socket.getOutputStream());
//            Input input = new Input(in, this);
//            Thread thread = new Thread(input);
//            thread.start();
//            Thread thread2 = new Thread(this);
//            thread2.start();
//        }
//        catch (Exception e){
//            System.out.println("Unable to start client");
//        }
//    }
//
//    public void updateCoordinates(int pid, int x2, int y2){
//        this.x[pid] = x2;
//        this.y[pid] = y2;
//    }
//    public void actionPerformed(ActionEvent e) {
//    }
//    public static void main(String[] args) {
//    JFrame frame = new JFrame("Light Cycles");
//    Display display = new Display();
//    LightCycle lightCycle = new LightCycle(100, Color.blue);
//    Timer timer = new Timer(24, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            lightCycle.move(display.getSize());
//            if (lightCycle.isWallActive()) {
//                ArrayList<Rectangle> rect = lightCycle.getTrail();
//                rect.add(new Rectangle(lightCycle.getX(), lightCycle.getY(), 30,30 ));
//                lightCycle.setTrail(rect);
//            }
//            lightCycle.move(display.getSize());
//            if (!lightCycle.isActive){
//                display.gameOver();
//            }
//
//            display.repaint();
//        }
//    });
//        timer.start();
//         display.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyCode());
//                //press 'W' to go up
//                if (e.getKeyCode() == 87) {
//                    lightCycle.yDir = -1;
//                    lightCycle.xDir = 0;
//
//                }
//                //press 'S' to go down
//                if (e.getKeyCode() == 83) {
//                    lightCycle.yDir = 1;
//                    lightCycle.xDir = 0;
//                }
//                //press 'A' to go left
//                if (e.getKeyCode() == 65) {
//                    lightCycle.yDir = 0;
//                    lightCycle.xDir = -1;
//                }
//                //press 'D' to go right
//                if (e.getKeyCode() == 68) {
//                    lightCycle.yDir = 0;
//                    lightCycle.xDir = 1;
//                }
//                //spacebar toggling jet wall
//                if (e.getKeyCode() == 32) {
//                    lightCycle.jetWall();
//                }
//                //Shift key increases speed
//                if (e.getKeyCode() == 16) {
//                    lightCycle.speed = lightCycle.speed + 1;
//                }
//                //'Z' key decreases speed
//                if (e.getKeyCode() == 90) {
//                    lightCycle.speed = lightCycle.speed - 1;
//                }
//                if (lightCycle.isActive) {
//                    try {
//                        //out.writeInt(playerId);
//                        out.writeInt(lightCycle.xDir);
//                        out.writeInt(lightCycle.yDir);
//                        out.writeInt(lightCycle.speed);
//                        //out.writeInt(lightCycle.isWallActive);
//                    } catch (Exception e1) {
//                        System.out.println("Error sending coordinates");
//                    }
//                }
//            }
//        });
//
///*____________________________Application window's sizing permissions/properties____________________________________*/
//        frame.add(display, BorderLayout.CENTER);
//
//        display.setLightCycle(lightCycle);
//
//        frame.pack();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        //    frame.setResizable(false);
//    }
//
//
//    public void run(){
//        while(true){
//
//            try{
//                Thread.sleep(400);
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//class Input implements Runnable {
//    DataInputStream in;
//    Client client;
//    public Input(DataInputStream in, Client c){
//        this.in = in;
//        this.client = c;
//    }
//    public void run(){
//        while (true){
//            String message;
//            try {
//                int playerId = in.readInt();
//                int x = in.readInt();
//                int y = in.readInt();
//                client.updateCoordinates(playerId, x, y);
//            }
//            catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}