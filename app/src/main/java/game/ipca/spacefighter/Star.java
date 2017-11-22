package game.ipca.spacefighter;

import java.util.Random;

/**
 * Created by lourencogomes on 22/11/17.
 */

public class Star {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;
    private int speed=0;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;

    public Star(int screenX, int screenY){
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed= generator.nextInt(10);

        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);

    }

    public void update(int playerSpeed){
        x-=playerSpeed;
        x-=speed;
        if (x<0){
            x=maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed=generator.nextInt(10);
        }
    }

    public float getStarWidth() {
        Random generator = new Random();
        return generator.nextFloat()*4.0f;
    }
}
