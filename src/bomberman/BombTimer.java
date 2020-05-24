package bomberman;

import java.util.Timer;
import java.util.TimerTask;

public class BombTimer extends Timer {

    private Bomberman game;
    public Bomb bombs;
    Task t;
    int hi;

    //Timer constructor
    public BombTimer(Bomb b) {
        super();
        bombs = b;
        t = new Task();
        scheduleAtFixedRate(t, 0, 50);
    }

    public class Task extends TimerTask {

        @Override
        public void run() {
            //If bomb is about to explode set variables accordingly
            if (bombs.checkState() == 1) {
                hi = 1;
                bombs.setbX();
                bombs.setbY();
                bombs.setExploding(true);
                //Reset bomb after explosion
            } else if (bombs.checkState() == 3) {
                bombs.makeNew();
            }
        }
    }

    //Returns bombs
    public Bomb getBombs() {
        return bombs;
    }

    //Sends a variable to the player class to queue an explosion detection
    public int getHi() {
        return hi;
    }

    //Resets the high variable
    public void setHi() {
        hi = 0;
    }
}
