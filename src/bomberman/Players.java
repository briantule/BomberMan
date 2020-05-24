package bomberman;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Players {

    //Declare variables
    private static final int WIDTH = 45, HEIGHT = 45;
    public BombTimer time;
    private final Bomberman game;

    private final int up, down, left, right, playerNum;
    private int x, xa;
    private int y, ya;
    private final int b;
    BufferedImage img;
    BufferedImage img1;

    //Player constructor
    public Players(int left, int up, int down, int right, int bomb, int playerNum, Bomberman game) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.playerNum = playerNum;
        this.game = game;
        this.b = bomb;

        //Creates a new bomb with a timer
        time = new BombTimer(new Bomb(1000, 1000, -1));

        //Correctly set the starting positions
        if (playerNum == 1) {
            x = 60;
            y = 60;
        } else {
            x = 560;
            y = 560;
        }
    }

    public void update() {
        //Stop user from leaving the area
        if (y == 60) {
            y -= ya;
            y = 61;
        } else if (y == 566) {
            y -= ya;
            y = 565;

        } else {
            y += ya;
        }

        if (x == 60) {
            x -= xa;
            x = 61;
        } else if (x == 566) {
            x -= xa;
            x = 565;

        } else {
            x += xa;
        }

        //Play sound when bomb explodes and destroy affected blocks
        if (time.getHi() == 1) {
            Sound.play("bomb.wav");
            for (int i = 0; i <= 33; i++) {
                if (game.getPanel().getBlocks(i).getBounds().intersects(time.bombs.getExplosionRight()) || game.getPanel().getBlocks(i).getBounds().intersects(time.bombs.getExplosionLeft()) || game.getPanel().getBlocks(i).getBounds().intersects(time.bombs.getExplosionUp()) || game.getPanel().getBlocks(i).getBounds().intersects(time.bombs.getExplosionDown())) {
                    game.getPanel().getBlocks(i).destroyBlock();
                }
            }

        }
        //Sets the high variable back to 0
        if (time.bombs.isExploding()) {
            time.setHi();
        }
        //Check for collision
        checkBedrockCollision();
        checkBlockCollision();
    }

    //Moves and drops bombs when keys are pressed
    public void pressed(int keyCode) {
        if (keyCode == up) {
            ya = -1;
        } else if (keyCode == down) {
            ya = 1;
        }
        if (keyCode == left) {
            xa = -1;
        } else if (keyCode == right) {
            xa = 1;
        }
        if (keyCode == b) {

            dropBomb();
        }
    }

    //Collision checking for bedrock
    public void checkBedrockCollision() {
        for (int i = 0; i <= 24; i++) {
            if (game.getPanel().getBedrock(i).getBounds().intersects(getBounds())) {
                if (x + 55 >= game.getPanel().getBedrock(i).getBounds().x && x + 55 <= game.getPanel().getBedrock(i).getBounds().x + 50) {
                    if (y > game.getPanel().getBedrock(i).getBounds().y - 44 && y < game.getPanel().getBedrock(i).getBounds().y + 49) {
                        x--;
                    }
                } else if (x >= game.getPanel().getBedrock(i).getBounds().x && x <= game.getPanel().getBedrock(i).getBounds().x + 50) {
                    if (y > game.getPanel().getBedrock(i).getBounds().y - 44 && y < game.getPanel().getBedrock(i).getBounds().y + 49) {
                        x++;
                    }

                }
                if (y + 50 >= game.getPanel().getBedrock(i).getBounds().y && y + 50 <= game.getPanel().getBedrock(i).getBounds().y + 50) {
                    if (x > game.getPanel().getBedrock(i).getBounds().x - 44 && x < game.getPanel().getBedrock(i).getBounds().x + 49) {
                        y--;
                    }
                } else if (y >= game.getPanel().getBedrock(i).getBounds().y && y <= game.getPanel().getBedrock(i).getBounds().y + 50) {
                    if (x > game.getPanel().getBedrock(i).getBounds().x - 44 && x < game.getPanel().getBedrock(i).getBounds().x + 49) {
                        y++;
                    }
                }

            }
        }
    }

    //Collision checking for blocks
    public void checkBlockCollision() {
        for (int i = 0; i <= 33; i++) {
            if (game.getPanel().getBlocks(i).getBounds().intersects(getBounds())) {
                if (x + 55 >= game.getPanel().getBlocks(i).getBounds().x && x + 55 <= game.getPanel().getBlocks(i).getBounds().x + 50) {
                    if (y > game.getPanel().getBlocks(i).getBounds().y - 44 && y < game.getPanel().getBlocks(i).getBounds().y + 49) {
                        x--;
                    }
                } else if (x >= game.getPanel().getBlocks(i).getBounds().x && x <= game.getPanel().getBlocks(i).getBounds().x + 50) {
                    if (y > game.getPanel().getBlocks(i).getBounds().y - 44 && y < game.getPanel().getBlocks(i).getBounds().y + 49) {
                        x++;
                    }

                }
                if (y + 50 >= game.getPanel().getBlocks(i).getBounds().y && y + 50 <= game.getPanel().getBlocks(i).getBounds().y + 50) {
                    if (x > game.getPanel().getBlocks(i).getBounds().x - 44 && x < game.getPanel().getBlocks(i).getBounds().x + 49) {
                        y--;
                    }
                } else if (y >= game.getPanel().getBlocks(i).getBounds().y && y <= game.getPanel().getBlocks(i).getBounds().y + 50) {
                    if (x > game.getPanel().getBlocks(i).getBounds().x - 44 && x < game.getPanel().getBlocks(i).getBounds().x + 49) {
                        y++;
                    }
                }

            }
        }
    }

    //Stop moving when keys are released
    public void released(int keyCode) {
        if (keyCode == up || keyCode == down) {
            ya = 0;
        }
        if (keyCode == left || keyCode == right) {
            xa = 0;
        }
    }

    //Sets bomb position and start the timer
    public void dropBomb() {
        if (time.bombs.getStartTime() == -1) {
            time.bombs.setX(x + 15);
            time.bombs.setY(y + 15);
            time.bombs.setStartTime(System.currentTimeMillis());
        }
    }

    //Return player boundaries
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
