import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    boolean isActive;
    private LightCycle lightCycle;
    private static final Font font = new Font("Calibri",Font.BOLD, 50);

    Display() {
        setFocusable(true);
        setPreferredSize(new Dimension(600,800));
        setBackground(new Color(0,0,0));
        isActive = true;
    }

    void setLightCycle(LightCycle lightCycle) {
        this.lightCycle = lightCycle;
    }

    public void gameOver(){
        isActive = false;
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (isActive) {
            if (lightCycle != null) {
                lightCycle.draw(graphics);
            }
        }
        else {
            graphics.setColor(Color.RED);
            // check and edit font size because it doesn't work on the private static thingy
            graphics.drawString("Game Over!", 300, 300);
        }
    }
}
