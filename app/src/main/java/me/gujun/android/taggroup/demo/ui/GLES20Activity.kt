package me.gujun.android.taggroup.demo.ui

import android.app.Activity
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

import me.gujun.android.taggroup.demo.R
import me.gujun.android.taggroup.demo.base.BaseActivity

const val TAG = "HelloPoints"

class GLES20Activity : AppCompatActivity() {
    private lateinit var glSurfaceView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Play with Points"

        glSurfaceView = GLSurfaceView(this)
        setContentView(glSurfaceView)

        glSurfaceView.setEGLContextClientVersion(2)  //设置2.0版本
        glSurfaceView.setRenderer(PointsRender)  //设置着色器
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY  //着色模式
        //设置一个Renderer实例，渲染模式(render mode)分为两种，
        // 一个是GLSurfaceView主动刷新(continuously)，不停的回调Renderer的onDrawFrame，
        // 另外一种叫做被动刷新（when dirty），就是当请求刷新时才调一次onDrawFrame。
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        glSurfaceView.onPause()
    }

    companion object PointsRender : GLSurfaceView.Renderer {
        private const val VERTEX_SHADER =   //定点着色器  类似于C语言
                "void main() {\n" +
                        "gl_Position = vec4(0.0, 0.0, 0.0, 1.0);\n" +
                        "gl_PointSize = 20.0;\n" +
                        "}\n"
//        gl_Position是一个内置变量，用于指定顶点，它是一个点，三维空间的点，
// 所以用一个四维向量来赋值。vec4是四维向量的类型，vec4()是它的构造方法。
//        先了解这么多，记得对于2D的话，第四位永远传1.0就可以了。
////        这里，是指定原点(0, 0, 0)作为顶点，就是说想在原点位置画一个点。
////        gl_PointSize是另外一个内置变量，用于指定点的大小。
//        这个shader就是想在原点画一个尺寸为20的点。
        private const val FRAGMENT_SHADER =  //片元着色器
                "void main() {\n" +
                        "gl_FragColor = vec4(1., 0., 0.0, 1.0);\n" +
                        "}\n"
//        gl_FragColor是fragment shader的内置变量，
//        用于指定当前顶点的颜色，四个分量（r, g, b, a）。这里是想指定为红色，不透明。
        private var mGLProgram: Int = -1

        override fun onDrawFrame(p0: GL10?) {  //绘制帧
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
            // 清除颜色缓冲区，因为我们要开始新一帧的绘制了，所以先清理，以免有脏数据。
            GLES20.glUseProgram(mGLProgram)
//            告诉OpenGL，使用我们在onSurfaceCreated里面准备好了的shader program来渲染
            GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1)
            // 开始渲染，发送渲染点的指令， 第二个参数是offset，第三个参数是点的个数。目前只有一个点，所以是1。
        }
//        此回调，会在surface发生改变时，通常是size发生变化。这里我们改变一下视角。
        override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {  //发生改变
            GLES20.glViewport(0, 0, p1, p2)
        }

        override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {  //created
            GLES20.glClearColor(0f, 0f, 0f, 1f)
//            给我把背景，或者叫作画布，画成黑色，不透明。比较绕人的说法是用参数指定的(r, g, b, a)
//            这个颜色来初始化颜色缓冲区（color buffer）。目前就理解成为画面背景色就可以了。

            val vsh = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
            GLES20.glShaderSource(vsh, VERTEX_SHADER)
            GLES20.glCompileShader(vsh)
//            创建一个vertex shader程序，返回的是它的句柄，此返回值会用在后续操作的参数，所以，要用变量记录下来。

            val fsh = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
            GLES20.glShaderSource(fsh, FRAGMENT_SHADER)
            GLES20.glCompileShader(fsh)
            //创建一个fragment shader程序，返回的是它的句柄，此返回值会用在后续操作的参数，所以，要用变量记录下来。

            mGLProgram = GLES20.glCreateProgram()
            GLES20.glAttachShader(mGLProgram, vsh)
            GLES20.glAttachShader(mGLProgram, fsh)
            GLES20.glLinkProgram(mGLProgram)

//            如果shader编译或者链接过程出错了怎么办呢？能不能提早发现呢？当然，有办法检查一下，就是用接下来的这几句：
            GLES20.glValidateProgram(mGLProgram)

            val status = IntArray(1)   //验证结果
            GLES20.glGetProgramiv(mGLProgram, GLES20.GL_VALIDATE_STATUS, status, 0)
            Log.d(TAG, "validate shader program: " + GLES20.glGetProgramInfoLog(mGLProgram))
//            如果有语法错误，编译错误，或者状态出错，这一步是能够检查出来的。如果一切正常，则取出来的status[0]为0。
        }

    }

}