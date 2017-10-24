import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
When player hits side, they die
When player icon collides with side, icon disappears, player can't control the icon (when invisible)
When the player "dies" their username gets sent to server.java (Sender.java) and placed in an array.
When the username is sent to the server and placed in an array, it pushes down other users in the array in order of last added.
When one player remains, the player's icon disappears, loses controls, and username and score (measured by the amount of 'walls' created [in pixels * 10]) added to the array
Last player to die is displayed, and score is arranged and the highest score player is displayed.
*/

class Actions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
/*____________________________Creates the game_____________________________________*/
public class GameWindow {
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
/*_____________________________Assigns keys to Light Cycle's movements____________________________________*/
        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyCode());
                //press 'UP' to go up
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    lightCycle.moveUp();
//                    lightCycle.speedReset();
//                }
//                //press 'DOWN' to go down
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    lightCycle.moveDown();
//                    lightCycle.speedReset();
//                }
//                //press 'LEFT' to go left
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    lightCycle.moveLeft();
//                    lightCycle.speedReset();
//                }
//                //press 'RIGHT' to go right
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    lightCycle.moveRight();
//                    lightCycle.speedReset();
//                }
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
                if (e.getKeyCode () == 32){
                    lightCycle.jetWall();
                }
                //Shift key increases speed
                if (e.getKeyCode() == 16){
                    lightCycle.speed = lightCycle.speed + 1;
                }
                //'Z' key decreases speed
                if (e.getKeyCode() == 90){
                    lightCycle.speed = lightCycle.speed - 1;
                }
            }
        });

/*____________________________Application window's sizing permissions/properties____________________________________*/
        frame.add(display, BorderLayout.CENTER);

        display.setLightCycle(lightCycle);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    //    frame.setResizable(false);
    }
}
