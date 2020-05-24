package bomberman;

import java.awt.Rectangle;

public class Bomb {

    //Declare variables
    private static final int WIDTH = 20, HEIGHT = 20;
    private long startTime;
    private boolean exploding;
    private int x, y;
    private int bx, by;
    private final int bw = 50, bh = 50;

    //Bomb constructor
    public Bomb(int x, int y, long time) {
        this.x = x;
        this.y = y;
        startTime = time;
        exploding = false;

    }

    //Returns the state of the bomb (exploding or not)
    public int checkState() {
        long time;
        int state;

        if (exploding) {
            time = 2000;
            state = 3;
        } else {
            time = 1500;
            state = 1;
        }
        //Have the bomb linger in a state until time is up
        if (startTime != -1 && System.currentTimeMillis() - startTime > time) {
            return state;
        } else {
            return state - 1;
        }

    }

    //Reset bomb
    public void makeNew() {
        x = 1000;
        y = 1000;
        startTime = -1;
        exploding = false;
    }

    //Sets start time
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    //Returns start time
    public long getStartTime() {
        return startTime;
    }

    //Sets x,y,bx,and by variables
    public void setX(int x) {
        this.x = x;
    }

    public void setbX() {
        bx = x;
    }

    public void setbY() {
        by = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Sets and returns if exploding
    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    public boolean isExploding() {
        return exploding;
    }

    //Return bomb boundaries
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    //Return explosion boundaries
    public Rectangle getExplosionLeft() {
        return new Rectangle(bx - 50, by, bw, bh - 20);
    }

    public Rectangle getExplosionRight() {
        return new Rectangle(bx + 20, by, bw, bh - 20);
    }

    public Rectangle getExplosionUp() {
        return new Rectangle(bx, by - 50, bw - 20, bh);
    }

    public Rectangle getExplosionDown() {
        return new Rectangle(bx, by + 20, bw - 20, bh);
    }

}
