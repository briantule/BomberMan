package bomberman;

import java.awt.Color;
import javax.swing.JFrame;

public class Bomberman extends JFrame {

    private final static int WIDTH = 700, HEIGHT = 700;
    private final GamePanel panel;

    //Constructor for jframe
    public Bomberman() {
        setSize(WIDTH, HEIGHT);
        setTitle("Bomberman");
        setBackground(Color.GREEN);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new GamePanel(this);
        add(panel);

    }

    //Returns jpanel
    public GamePanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {

    }
}
