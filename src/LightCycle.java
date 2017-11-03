import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.*;


class LightCycle {
    private int x, y;
    int xDir, yDir;

    private final int size;
    int speed;
    private final Color color;

    public boolean isActive;
    public boolean isWallActive;
    private final boolean isSpeedy;
    private ArrayList<Rectangle> trail = new ArrayList<Rectangle>();

    int speedCount;
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
    public void setTrail(ArrayList<Rectangle> trail) {
        this.trail = trail;
    }


/*_____________________________Light Cycle properties____________________________________*/
    LightCycle(int size, Color color) {
        isActive = true;
        x = 100;
        y = 300;
        xDir = 0;
        yDir = 0;
        speed = 0;
        this.size = size;
        this.color = Color.blue;
    }

/*_______________________________Toggling Jet Wall__________________________________*/

    void jetWall() {
        if (isWallActive == true) {
            isWallActive = false;
            System.out.println("Jetwall is off");
        } else {
            isWallActive = true;
            System.out.println("Jetwall is on");
        }
    }

///*_____________________Light Cycle's directions when movement keys pressed___________________________*/
//    void moveUp() {
//        yDir = -5;
//        xDir = 0;
//    }
//
//    void moveDown() {
//        yDir = 5;
//        xDir = 0;
//    }
//
//    void moveLeft() {
//        yDir = 0;
//        xDir = -5;
//    }
//
//    void moveRight() {
//        yDir = 0;
//        xDir = 5;
//    }

/*____________________________Border collisions_____________________________________*/
    void move(Dimension size) {
        x = x + xDir * speed;
        y = y + yDir * speed;


        if (xDir > 0 && x + this.size / 2 > size.width) {
            xDir = 0;
            yDir = 0;
            x -= 5;
            isActive = false;
        }
        if (xDir < 0 && x - this.size / 7 < 0) {
            xDir = 0;
            yDir = 0;
            x += 5;
            isActive = false;
        }
        if (yDir > 0 && y + this.size / 2 > size.height) {
            xDir = 0;
            yDir = 0;
            y -= 5;
            isActive = false;
        }
        if (yDir < 0 && y - this.size < 0) {
            xDir = 0;
            yDir = 0;
            y += 5;
            isActive = false;
        }
    }

/*_____________________________Creates Light Cycle sprite____________________________________*/
    void draw(Graphics graphics) {
        int topLeftX = x - 2;
        int topLeftY = y - 2;



        Graphics localGraphics = graphics.create();
        localGraphics.setColor(color);
        localGraphics.fillRect(topLeftX, topLeftY,
                30, 30);
        localGraphics.drawRect(topLeftX, topLeftY, 30, 30);
        localGraphics.dispose();

    }
}
