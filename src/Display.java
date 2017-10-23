import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Display extends JPanel {

    boolean isActive;
    private LightCycle lightCycle;
    private static final Random random = new Random();
    private static final Font font = new Font("Calibri", Font.BOLD, 50);
    int collision = 0;
    JFrame frame;


    void setLightCycle(LightCycle lightCycle) {
        this.lightCycle = lightCycle;
    }

    public void gameOver() {
        isActive = false;
    }
/*______________________________Application window's properties___________________________________*/
    Display() {
        setFocusable(true);
        setPreferredSize(new Dimension(600, 800));
        setBackground(new Color(0, 0, 0));
        isActive = true;
    }
/*____________________________Jet Wall properties_____________________________________*/
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int forceField = 5 + random.nextInt(16);

        ArrayList<Rectangle> rects = lightCycle.getTrail();
        for (Rectangle rectangle : rects) {
            graphics.setColor(Color.RED);
            graphics.drawRect(rectangle.x - 2, rectangle.y - 2, 30, 30);
            graphics.fillRect(rectangle.x + forceField / 2, rectangle.y + forceField / 2, 30 - forceField, 30 - forceField);
        }

/*_______________________Checks if the Light Cycle sprite hasn't collided with any objects_________________________________*/
        if (isActive) {
            if (lightCycle != null) {
                lightCycle.draw(graphics);
            }
        } else {
            collision += 1;
        }
        if (collision == 1) {
            int replayOptionBox = JOptionPane.showConfirmDialog(frame,
                    "Do you want to play again?");
            if (replayOptionBox == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Finding another player...");
                GameWindow.main(new String[0]);
            }
            else {
                JOptionPane.showMessageDialog(null, "Returning to Menu...");
                System.exit(0);
            }
        }
    }
}

