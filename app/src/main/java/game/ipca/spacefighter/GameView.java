package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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


    int score = 0;
    int lives = 5;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;

    private Player player;
    private ArrayList<Star> stars= new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Boom boom;

    private int scrWidth,scrHeight;

    private Context context;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.context=context;
        scrWidth=screenX;
        scrHeight=screenY;

        holder=getHolder();
        paint=new Paint();
        paint.setTextSize(72f);

        player=new Player(
                BitmapFactory.decodeResource(context.getResources(),R.drawable.player),
                screenX,
                screenY);

        boom = new Boom(
                BitmapFactory.decodeResource(context.getResources(),R.drawable.boom),
                screenX,
                screenY);

        for (int i=0; i<100; i++){
            Star s=new Star(screenX,screenY);
            stars.add(s);
        }

        for (int i=0; i<3; i++){
            Enemy enemy= new Enemy(
                    BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy),
                    screenX,
                    screenY);
            enemies.add(enemy);
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
        player.update(0);

        boom.offScreen();

        for (Star s : stars){
            s.update(player.getSpeed());
        }

        for (Enemy e: enemies){

            if (e.isReachTheEndOfTheSreen()){
                e.setReachTheEndOfTheSreen(false);
                lives--;
                e.goToStarPoint();
            }

            if (lives < 1){
                pause();
            }

            e.update(player.getSpeed());


            if(Rect.intersects(player.getDetectCollision(),e.getDetectCollision())){
                score += 10;
                boom.setX(e.getX());
                boom.setY(e.getY());
                e.setX(-300);
                e.goToStarPoint();
            }

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

            for(Enemy e: enemies){
                canvas.drawBitmap(e.getBitmap(),e.getX(),e.getY(),paint);
            }

            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);
            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("Lives: "+ lives, 60f,60f, paint);
            canvas.drawText("Score: "+ score, scrWidth -400 ,60f, paint);



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
        playing=false;
        gameThread.interrupt();

    }

    public void resume() {
        playing=true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}
