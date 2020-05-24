package bomberman;

//Imports
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    //Declaring variables
    private final Bomberman game;
    Bedrock[] bedrock = new Bedrock[25];
    Blocks[] blocks = new Blocks[34];
    private final Players player1, player2;
    private BufferedImage playerImage = null;
    private BufferedImage bedrockImage = null;
    private BufferedImage blocksImage = null;
    private BufferedImage bombImage = null;
    private BufferedImage fireDownImage = null;
    private BufferedImage fireUpImage = null;
    private BufferedImage fireLeftImage = null;
    private BufferedImage fireRightImage = null;
    private BufferedImage explosionImage = null;
    private BufferedImage BrickImage = null;

    public GamePanel(Bomberman game) {

        //Load images
        playerImage = Images.loadImage("images/player.png");
        bedrockImage = Images.loadImage("images/stoneTile1.png");
        blocksImage = Images.loadImage("images/stoneTile2.png");
        bombImage = Images.loadImage("images/bomb1.png");
        fireDownImage = Images.loadImage("images/fireDown.png");
        fireUpImage = Images.loadImage("images/fireUp.png");
        fireLeftImage = Images.loadImage("images/fireLeft.png");
        fireRightImage = Images.loadImage("images/fireRight.png");
        BrickImage = Images.loadImage("images/topBrick.png");
        explosionImage = Images.loadImage("images/bombExplosion.png");
        setBackground(Color.lightGray);
        this.game = game;

        //Start backround music
        Sound.play("bgm.wav");

        //Create impassable terrain
        int z = 1;
        for (int i = 1; i <= 25; i++) {
            if (z == 6) {
                z = 1;
            }
            if (i <= 5) {
                bedrock[i - 1] = new Bedrock(100 * z + 10, 110);
            } else if (i <= 10) {
                bedrock[i - 1] = new Bedrock(100 * z + 10, 210);
            } else if (i <= 15) {
                bedrock[i - 1] = new Bedrock(100 * z + 10, 310);
            } else if (i <= 20) {
                bedrock[i - 1] = new Bedrock(100 * z + 10, 410);
            } else if (i <= 25) {
                bedrock[i - 1] = new Bedrock(100 * z + 10, 510);
            }
            z++;
        }

        //Create destructable blocks
        blocks[0] = new Blocks(160, 60);
        blocks[1] = new Blocks(210, 60);
        blocks[2] = new Blocks(60, 160);
        blocks[3] = new Blocks(460, 60);
        blocks[4] = new Blocks(460, 110);
        blocks[5] = new Blocks(460, 160);
        blocks[6] = new Blocks(60, 210);
        blocks[7] = new Blocks(60, 260);
        blocks[8] = new Blocks(210, 260);
        blocks[9] = new Blocks(260, 210);
        blocks[10] = new Blocks(210, 360);
        blocks[11] = new Blocks(110, 360);
        blocks[12] = new Blocks(160, 560);
        blocks[13] = new Blocks(210, 560);
        blocks[14] = new Blocks(260, 560);
        blocks[15] = new Blocks(460, 560);
        blocks[16] = new Blocks(560, 460);
        blocks[17] = new Blocks(410, 360);
        blocks[18] = new Blocks(360, 510);
        blocks[19] = new Blocks(260, 510);
        blocks[20] = new Blocks(160, 510);
        blocks[21] = new Blocks(410, 360);
        blocks[22] = new Blocks(410, 260);
        blocks[23] = new Blocks(460, 210);
        blocks[24] = new Blocks(210, 160);
        blocks[25] = new Blocks(160, 160);
        blocks[26] = new Blocks(510, 160);
        blocks[27] = new Blocks(360, 210);
        blocks[28] = new Blocks(560, 210);
        blocks[29] = new Blocks(560, 260);
        blocks[30] = new Blocks(510, 260);
        blocks[31] = new Blocks(260, 410);
        blocks[32] = new Blocks(360, 410);
        blocks[33] = new Blocks(60, 460);

        //Create players
        player1 = new Players(KeyEvent.VK_A, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_B, 1, game);
        player2 = new Players(KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_P, 2, game);

        //Start timer
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);

    }

    //Returns bedrock
    public Bedrock getBedrock(int bedrockNum) {
        return bedrock[bedrockNum];
    }

    //Returns blocks
    public Blocks getBlocks(int blocksNum) {
        return blocks[blocksNum];
    }

    //Checks for collisions
    private void update() {
        player1.update();
        player2.update();
        victoryCheck();

    }

    //The constant loop
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    //If collision was found with explosion output victory and close game
    public void victoryCheck() {
        if (player1.time.bombs.isExploding()) {
            if (player1.getBounds().intersects(player1.time.bombs.getExplosionRight()) || player1.getBounds().intersects(player1.time.bombs.getExplosionLeft()) || player1.getBounds().intersects(player1.time.bombs.getExplosionUp()) || player1.getBounds().intersects(player1.time.bombs.getExplosionDown())) {
                Sound.play("victory.wav");
                JOptionPane.showMessageDialog(null, "Player 2 wins!");
                System.exit(0);
            } else if (player2.getBounds().intersects(player1.time.bombs.getExplosionRight()) || player2.getBounds().intersects(player1.time.bombs.getExplosionLeft()) || player2.getBounds().intersects(player1.time.bombs.getExplosionUp()) || player2.getBounds().intersects(player1.time.bombs.getExplosionDown())) {
                Sound.play("victory.wav");
                JOptionPane.showMessageDialog(null, "Player 1 wins!");
                System.exit(0);
            }

        }

        //Sane as previous check except for the second player's bomb
        if (player2.time.bombs.isExploding()) {
            if (player1.getBounds().intersects(player2.time.bombs.getExplosionRight()) || player1.getBounds().intersects(player2.time.bombs.getExplosionLeft()) || player1.getBounds().intersects(player2.time.bombs.getExplosionUp()) || player1.getBounds().intersects(player2.time.bombs.getExplosionDown())) {
                Sound.play("victory.wav");
                JOptionPane.showMessageDialog(null, "Player 2 wins!");
                System.exit(0);

            } else if (player2.getBounds().intersects(player2.time.bombs.getExplosionRight()) || player2.getBounds().intersects(player2.time.bombs.getExplosionLeft()) || player2.getBounds().intersects(player2.time.bombs.getExplosionUp()) || player2.getBounds().intersects(player2.time.bombs.getExplosionDown())) {
                Sound.play("victory.wav");
                JOptionPane.showMessageDialog(null, "Player 1 wins!");
                System.exit(0);
            }
        }
    }

    //Checks for keyboard input
    @Override
    public void keyPressed(KeyEvent e) {
        player1.pressed(e.getKeyCode());
        player2.pressed(e.getKeyCode());
    }

    @Override

    public void keyReleased(KeyEvent e) {
        player1.released(e.getKeyCode());
        player2.released(e.getKeyCode());
    }

    @Override

    public void keyTyped(KeyEvent e) {
        ;
    }

    //Returns players
    public Players getPlayers(int playerNum) {
        if (playerNum == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    //Paints the players, bombs, and terrain
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.gray);
        for (int i = 0; i <= 33; i++) {
            g.drawImage(blocksImage, blocks[i].getBounds().x, blocks[i].getBounds().y, null);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 600; i += 50) {
            g.drawImage(BrickImage, i + 10, 10, null);
            g.drawImage(BrickImage, 10, i + 10, null);
            g.drawImage(BrickImage, i + 10, 610, null);
            g.drawImage(BrickImage, 610, i + 10, null);
        }

        g.drawImage(playerImage, player1.getBounds().x, player1.getBounds().y, null);
        g.drawImage(playerImage, player2.getBounds().x, player2.getBounds().y, null);
        g.drawRect(player1.getBounds().x, player1.getBounds().y, 45, 45);
        g.drawRect(player2.getBounds().x, player2.getBounds().y, 45, 45);
        for (int i = 0; i <= 24; i++) {
            g.drawImage(bedrockImage, bedrock[i].getBounds().x, bedrock[i].getBounds().y, null);
        }

        g.drawImage(bombImage, player2.time.bombs.getBounds().x, player2.time.bombs.getBounds().y, null);
        if (player1.time.bombs.isExploding() == true) {
            g.drawImage(fireDownImage, player1.time.bombs.getExplosionDown().x, player1.time.bombs.getExplosionDown().y, null);
            g.drawImage(fireUpImage, player1.time.bombs.getExplosionUp().x, player1.time.bombs.getExplosionUp().y, null);
            g.drawImage(fireLeftImage, player1.time.bombs.getExplosionLeft().x, player1.time.bombs.getExplosionLeft().y, null);
            g.drawImage(fireRightImage, player1.time.bombs.getExplosionRight().x, player1.time.bombs.getExplosionRight().y, null);
        }
        if (player2.time.bombs.isExploding() == true) {
            g.drawImage(fireDownImage, player2.time.bombs.getExplosionDown().x, player2.time.bombs.getExplosionDown().y, null);
            g.drawImage(fireUpImage, player2.time.bombs.getExplosionUp().x, player2.time.bombs.getExplosionUp().y, null);
            g.drawImage(fireLeftImage, player2.time.bombs.getExplosionLeft().x, player2.time.bombs.getExplosionLeft().y, null);
            g.drawImage(fireRightImage, player2.time.bombs.getExplosionRight().x, player2.time.bombs.getExplosionRight().y, null);
        }
        if (player1.time.bombs.isExploding() == true) {
            g.drawImage(explosionImage, player1.time.bombs.getBounds().x - 5, player1.time.bombs.getBounds().y - 5, null);
        } else {
            g.drawImage(bombImage, player1.time.bombs.getBounds().x, player1.time.bombs.getBounds().y, null);
        }
        if (player2.time.bombs.isExploding() == true) {
            g.drawImage(explosionImage, player2.time.bombs.getBounds().x - 5, player2.time.bombs.getBounds().y - 5, null);
        } else {
            g.drawImage(bombImage, player2.time.bombs.getBounds().x, player2.time.bombs.getBounds().y, null);
        }
    }
}
