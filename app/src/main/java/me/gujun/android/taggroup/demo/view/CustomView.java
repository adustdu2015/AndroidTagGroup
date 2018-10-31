package me.gujun.android.taggroup.demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView  extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        这是第一部分
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
//        style分三类 FILL 填充 默认的
        //STROKE 画线模式
        //FILL_AND_STROKE 画线和填充
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);  // 抗锯齿，是边缘比较平滑
        paint.setStrokeWidth(30f);
        canvas.drawCircle(200,200,90,paint);

        //STROKE画线模式和  FILL_AND_STROKE 画线和填充 可以设置画线的宽度


//        Rect 与Rectf的区别为Rectf可以是float类型的

        //第二部分 画矩形
        Rect rect = new Rect();
        rect.left = 300;
        rect.top = 200;
        rect.right = 500;
        rect.bottom = 500;
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE); //划线
        paint1.setColor(Color.BLACK);
        paint1.setStrokeWidth(10f);
        canvas.drawRect(rect,paint1);   //画出一个边框为10 ，黑色的边框


        //画点
        Paint paint2 = new Paint();
        paint2.setStrokeCap(Paint.Cap.ROUND); //点的形状 ROUND为圆形  BUTT为方形的
        paint2.setColor(Color.BLACK);
        paint2.setStrokeWidth(20f);
        canvas.drawPoint(100,500,paint2);//画点

        Paint paint3  = new Paint();
        paint3.setStrokeCap(Paint.Cap.SQUARE);
        paint3.setStrokeWidth(30f);
        canvas.drawPoint(100,550f,paint3);

        //绘制多个点
        float[] points = {0,0,100,100,200,200,300,300,400,400};
        paint3.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoints(points,2,8,paint3); //点集合 偏移2个 一共8个值（4个点）

        //绘制椭圆
        paint3.setStyle(Paint.Style.STROKE);
        canvas.drawOval(300,300,600,500,paint3); //只能绘制横的或者竖的椭圆
        //当然也可以使用recf绘制椭圆
        //

        //绘制线  开始(x,y) 结束的 （x,y）
        canvas.drawLine(400,400,600,600,paint3);
        //绘制多个线
        float [] lines = {20,20,400,20,400,20,400,400};
        canvas.drawLines(lines,paint3);

        //绘制圆角矩形
        canvas.drawRoundRect(400,900,900,1000,40,40,paint3);

        //绘制弧形  startAngle开始的角度 划过的角度 ，是否连接中心点（true则为扇形，false则为弧形）
        canvas.drawArc(200,600,600,1200,0,90,false,paint3);

        //绘制Path绘制路径
        Path path = new Path();
        path.addCircle(100,1300,50,Path.Direction.CW);  //绘制路径 CW顺时针 CCW为逆时针
        canvas.drawPath(path,paint3);

        //
        String text = "adustdu2015";
        paint3.setColor(Color.parseColor("#4bc0c3"));
        paint3.setTextSize(40f);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(8f);
        canvas.drawText(text,100,1400,paint3);


    }
}
