package me.gujun.android.taggroup.demo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import me.gujun.android.taggroup.demo.R

class Lesson : AppCompatActivity() {
    private var mOpenGLView: OpenGLView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        //去标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //设置全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mOpenGLView = OpenGLView(this)
        setContentView(mOpenGLView)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}
