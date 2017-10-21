import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<Rectangle> rects = lightCycle.getTrail();
        for (Rectangle rectangle : rects ) {
            graphics.setColor(Color.RED);
            graphics.drawRect(rectangle.x,rectangle.y, 30,30);
            graphics.fillRect(rectangle.x,rectangle.y, 30,30);
        }

        if (isActive) {
            if (lightCycle != null) {
                lightCycle.draw(graphics);
//                graphics.drawImage()
                //to insert an image
            }
        }
        else {
            graphics.setColor(Color.RED);
            // check and edit font size because it doesn't work on the private static thingy
            graphics.drawString("Game Over!", 300, 300);
        }
    }
}
