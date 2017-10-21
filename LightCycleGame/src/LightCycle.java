import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


class LightCycle {
    private int x, y;
    private int xDir, yDir;

    private int size;
    private Color color;

    public boolean isActive;
    private boolean isWallActive = false;

    private ArrayList<Rectangle> trail = new ArrayList<Rectangle>();



    LightCycle(int size, Color color) {
        isActive = true;
        x = 100;
        y = 300;
        xDir = 0;
        yDir = 0;
        this.size = size;
        this.color = Color.blue;
    }


    void jetWall() {
        if (isWallActive == true) {
            isWallActive = false;
            System.out.println("Jetwall is off");
        } else {
            isWallActive = true;
            System.out.println("Jetwall is on");
        }
    }


    void moveUp() {
        yDir = -5;
        xDir = 0;
    }

    void moveDown() {
        yDir = 5;
        xDir = 0;
    }

    void moveLeft() {
        yDir = 0;
        xDir = -5;
    }

    void moveRight() {
        yDir = 0;
        xDir = 5;
    }

    void move(Dimension size) {
        x = x + xDir;
        y = y + yDir;


        if ((xDir > 0 && x + this.size / 2 > size.width)) {
            xDir = 0;
            yDir = 0;
            x -= 5;
            isActive = false;
        }
        if ((xDir < 0 && x - this.size / 7 < 0)) {
            xDir = 0;
            yDir = 0;
            x += 5;
            isActive = false;
        }
        if ((yDir > 0 && y + this.size / 2 > size.height)) {
            xDir = 0;
            yDir = 0;
            y -= 5;
            isActive = false;
        }
        if ((yDir < 0 && y - this.size < 0)) {
            xDir = 0;
            yDir = 0;
            y += 5;
            isActive = false;
        }
    }

    void draw(Graphics graphics) {
        int topLeftX = x - 2;
        int topLeftY = y - 2;
//        int insetSize = 5 + random.nextInt(16);

        Graphics localGraphics = graphics.create(); // isolate changes here for: colour, transform, etc.
        localGraphics.setColor(color);
        localGraphics.fillRect(topLeftX, topLeftY,
                30, 30);
        localGraphics.drawRect(topLeftX, topLeftY, 30, 30);
        localGraphics.dispose();

    }

    //Get methods

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isWallActive() {
        return isWallActive;
    }

    public ArrayList<Rectangle> getTrail() {
        return trail;
    }

    //Set methods

    public void setTrail(ArrayList<Rectangle> trail) {
        this.trail = trail;
    }
}
