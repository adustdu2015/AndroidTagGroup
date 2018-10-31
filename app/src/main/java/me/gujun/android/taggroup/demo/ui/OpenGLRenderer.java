package me.gujun.android.taggroup.demo.ui;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private float r,g,b,a;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glShadeModel(GL10.GL_SMOOTH); //启动阴影平滑

        gl.glClearColor(0f, 0f, 0f, 0f);  //设置清除屏幕时所用的颜色

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);//关于depth buffer(深度缓存)的 几乎所有在屏幕上显示3D场景OpenGL程序都使用深度缓存。它的排序决定那个物体先画

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); //告诉OpenGL我们希望进行最好的透视修正
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT); //清除屏幕和深度缓存。
        gl.glLoadIdentity();  //重置当前的模型观察矩阵。
    }

    /**
     * 设置颜色
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public void setColor(float r , float g ,float b, float a){
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
    }
}
