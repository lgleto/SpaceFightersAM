package game.ipca.spacefighter;

import android.graphics.Bitmap;

/**
 * Created by lourencogomes on 29/11/17.
 */

public class Boom extends Sprite {

    protected Boom(Bitmap bitmap, int scrWidth, int scrHeight) {
        super(bitmap, scrWidth, scrHeight);
        offScreen();
    }

    public void offScreen(){
        x=-250;
        y=-250;
    }
}
