package game.ipca.spacefighter;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by lourencogomes on 29/11/17.
 */

public abstract class Sprite {

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    protected Bitmap bitmap;
    protected int x;
    protected int y;
    protected int speed=0;
    protected int maxY;
    protected int minY;
    protected int maxX;
    protected int minX;



    protected Rect detectCollision;

    protected Sprite(int scrWidth, int scrHeight){
        maxX = scrWidth;
        maxY = scrHeight;
        minX = 0;
        minY = 0;
    }

    protected Sprite(Bitmap bitmap, int scrWidth, int scrHeight){
        this.bitmap=bitmap;
        maxX = scrWidth  - bitmap.getWidth();
        maxY = scrHeight - bitmap.getHeight();
        minX = 0;
        minY = 0;
        detectCollision  = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update(int playerSpeed){
        if (bitmap!=null){
            detectCollision.left=x;
            detectCollision.top=y;
            detectCollision.right=x+bitmap.getWidth();
            detectCollision.bottom=y+bitmap.getHeight();
        }
    }
}
