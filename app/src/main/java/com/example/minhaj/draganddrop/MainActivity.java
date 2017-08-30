package com.example.minhaj.draganddrop;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int x,y;
    private int screenWidth,screenHeight;
    private int imgViewW,imgViewH;
    private final String TAG  = "tag";
    private ImageView imageView;

    public MainActivity(){
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        Log.d(TAG,"sc:"+screenWidth+screenHeight);

    }

    @Override
    protected void onResume() {
        super.onResume();
        imgViewW = getResources().getDimensionPixelSize(R.dimen.img_width);
        imgViewH = getResources().getDimensionPixelSize(R.dimen.img_height);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        Log.d(TAG,"sce:"+screenWidth+screenHeight);

        imageView = (ImageView) findViewById(R.id.image);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG,"action down"+motionEvent.getX()+","+motionEvent.getY());
                        //setPosition((int)motionEvent.getX(),(int)motionEvent.getY());
                        //dragImage(motionEvent.getX(),motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG,"action up"+motionEvent.getX()+","+motionEvent.getY());
                        dragImage(motionEvent.getX(),motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG,"action move"+motionEvent.getX()+","+motionEvent.getY());
                        setPosition((int)motionEvent.getX(),(int)motionEvent.getY());
                        dragImage(motionEvent.getX(),motionEvent.getY());
                        break;
                }
                return true;
            }
        });
    }

    private void dragImage(float x,float y){
        float posX,posY;
        posX = imageView.getX()+this.x;
        posY = imageView.getY()+this.y;

        Log.d(TAG,"sc"+screenHeight);
        if (posX + imgViewW > screenWidth) {
            return;
        }
        Log.d(TAG,"he"+posY+"+"+imgViewH);
        if (posY +imgViewH >screenHeight){
            return;
        }
        imageView.setX(posX);
        imageView.setY(posY);
    }

    private void setPosition(int x , int y){
        this.x = x;
        this.y = y;
    }
}