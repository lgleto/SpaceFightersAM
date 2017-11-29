package game.ipca.spacefighter;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by lourencogomes on 29/11/17.
 */

public class Enemy extends Sprite {

    public boolean isReachTheEndOfTheSreen() {
        return reachTheEndOfTheSreen;
    }

    public void setReachTheEndOfTheSreen(boolean reachTheEndOfTheSreen) {
        this.reachTheEndOfTheSreen = reachTheEndOfTheSreen;
    }

    boolean reachTheEndOfTheSreen=false;

    protected Enemy(Bitmap bitmap, int scrWidth, int scrHeight) {
        super(bitmap, scrWidth, scrHeight);
        goToStarPoint();
    }

    public void goToStarPoint(){
        Random generator=new Random();
        speed = generator.nextInt(6)+10;
        x = maxX+bitmap.getWidth();
        y = generator.nextInt(maxY);
    }

    @Override
    public void update(int playerSpeed) {
        super.update(playerSpeed);
        x -= playerSpeed;
        x -= speed;
        if(x < minX - bitmap.getWidth()){
            reachTheEndOfTheSreen=true;
        }
    }


}
