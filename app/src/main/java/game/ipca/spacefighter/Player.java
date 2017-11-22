package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by lourencogomes on 22/11/17.
 */

public class Player {

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    private Bitmap bitmap;
    private int x;
    private int y;

    private boolean boosting=false;

    private int speed=0;

    private int maxY;
    private int minY;

    private static final int GRAVITY = 10;
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 20;

    public Player(Context context, int screenX, int screenY){
        x=75;
        y=50;
        speed=1;
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.player);

        maxY=screenY;
        minY=0;
    }

    public void update(){
       if(boosting)speed +=2;
       else speed -=5;
       if(speed > MAX_SPEED) speed = MAX_SPEED;
       if(speed < MIN_SPEED) speed = MIN_SPEED;

       y -=speed+GRAVITY;

       if (y<minY) y=minY;
       if (y>maxY) y=maxY;
    }

    public void setBoosting(){
        boosting=true;
    }

    public void stopBoosting(){
        boosting=false;
    }

}
