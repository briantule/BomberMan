package bomberman;

import java.awt.Rectangle;

public class Blocks {

    private static final int WIDTH = 50, HEIGHT = 50;
    private int x, y;

    public Blocks(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Move block offscreen and play destruction noise
    public void destroyBlock() {
        this.x = 1000;
        this.y = 1000;
        Sound.play("block.wav");
    }

    //Return block boundaries
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
