package bomberman;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bedrock {

    private static final int WIDTH = 50, HEIGHT = 50;
    private final int x, y;

    public Bedrock(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
