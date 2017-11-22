package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lourencogomes on 22/11/17.
 */

public class GameView extends SurfaceView implements Runnable {

    private boolean playing = false;

    private Thread gameThread = null;

    Player player;


    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player=new Player(context, screenX, screenY);


        holder=getHolder();
        paint=new Paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.setBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.stopBoosting();
                break;
        }
        return true;

    }

    @Override
    public void run() {
        while (playing){
            update();
            draw();
            controls();
        }
    }

    private void update() {
        player.update();
    }

    private void draw() {
        if(holder.getSurface().isValid()){
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);

            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void controls() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        playing=true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}
