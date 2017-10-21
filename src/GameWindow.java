import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


// When player hits side, they die
//When player icon collides with side, icon disappears, player can't control the icon (when invisible)
// When the player "dies" their username gets sent to server.java (Sender.java) and placed in an array.
//When the username is sent to the server and placed in an array, it pushes down other users in the array in order of last added.
//When one player remains, the player's icon disappears, loses controls, and username and score (measured by the amount of 'walls' created [in pixels * 10]) added to the array
//Last player to die is displayed, and score is arranged and the highest score player is displayed.
class Actions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

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
                if (!lightCycle.isActive){
                    display.gameOver();
                }

                display.repaint();
            }
        });
        timer.start();

        display.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    lightCycle.moveUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lightCycle.moveDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    lightCycle.moveLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    lightCycle.moveRight();
                }
                //press 'W' to go up
                if (e.getKeyCode() == 87) {
                    lightCycle.moveUp();
                }
                //press 'S' to go down
                if (e.getKeyCode() == 83) {
                    lightCycle.moveDown();
                }
                //press 'A' to go left
                if (e.getKeyCode() == 65) {
                    lightCycle.moveLeft();
                }
                //press 'D' to go right
                if (e.getKeyCode() == 68) {
                    lightCycle.moveRight();
                }
                //spacebar toggling jet wall
                if (e.getKeyCode () == 32){
                    lightCycle.jetWall();
                }
                //Shift key toggling speed
                if (e.getKeyCode() == 16){
                    lightCycle.speedStat();
                }

            }
        });


        frame.add(display, BorderLayout.CENTER);

        display.setLightCycle(lightCycle);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}