package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by lourencogomes on 22/11/17.
 */

public class Player extends Sprite {

    private boolean boosting=false;

    private static final int GRAVITY = -10;
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 20;

    public Player(Bitmap bitmap, int screenX, int screenY){
        super(bitmap,screenX,screenY);
        x=75;
        y=50;
        speed=1;
    }

    @Override
    public void update(int playerSpeed){

        super.update(playerSpeed);
       if(boosting)
           speed +=2;
       else
           speed -=5;
       if(speed > MAX_SPEED) speed = MAX_SPEED;
       if(speed < MIN_SPEED) speed = MIN_SPEED;

       y -=speed+GRAVITY;

       if (y>maxY) y=maxY;
       if (y<minY) y=minY;

    }

    public void setBoosting(){
        boosting=true;
    }

    public void stopBoosting(){
        boosting=false;
    }

}
