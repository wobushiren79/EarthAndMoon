package com.game.apple.earthandmoon;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    GLSurfaceView glSurfaceView;
    TheRender theRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);
        theRender = new TheRender(this);
        glSurfaceView.setRenderer(theRender);
        setContentView(glSurfaceView);
    }

   float xdang;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();

        switch (e.getAction()) {

            case MotionEvent.ACTION_MOVE:
                Log.v("this", "x:" + x);
                theRender.setAngle(-(xdang-x) / 10f);

        }
        xdang=x;
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
