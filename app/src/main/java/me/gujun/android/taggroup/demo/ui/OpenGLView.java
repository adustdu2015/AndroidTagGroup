package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLView  extends GLSurfaceView {
    private OpenGLRenderer mRenderer;
    public OpenGLView(Context context) {
        super(context);
        mRenderer = new OpenGLRenderer();
        setRenderer(mRenderer);
    }

    /**
     * view的触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        触发事件
        queueEvent(() -> mRenderer.setColor(event.getX()/getWidth(), event.getY()/getHeight(), 1.0f,0.0f));
        return super.onTouchEvent(event);
    }
}
