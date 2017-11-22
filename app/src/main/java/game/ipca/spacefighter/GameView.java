package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

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

    private ArrayList<Star> stars= new ArrayList<>();

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player=new Player(context, screenX, screenY);


        holder=getHolder();
        paint=new Paint();

        for (int i=0; i<100; i++){
            Star s=new Star(screenX,screenY);
            stars.add(s);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
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

        for (Star s : stars){
            s.update(player.getSpeed());
        }
    }

    private void draw() {
        if(holder.getSurface().isValid()){
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);

            for (Star s : stars){

                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }


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
