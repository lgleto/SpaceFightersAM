package game.ipca.spacefighter;

import java.util.Random;

/**
 * Created by lourencogomes on 22/11/17.
 */

public class Star extends Sprite{

    public Star(int screenX, int screenY){
        super(screenX,screenY);
        Random generator = new Random();
        speed= generator.nextInt(10);

        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);

    }

    @Override
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
