package com.draw.heart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * Created by wph on 2018/3/7  pm  15:00.
 * 绘制心电波形
 */

public class PainView extends CardiographView {

    private String m = " ";
    private float [][] oldpint = new float[781][4];//创建上一段需要显示的点
    LastHeart lh = new LastHeart();

    private Paint p;//创建画笔
    private int i = 0;
    private int startx, starty, stopx, stopy;//线坐标
    private int n = 0;


    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public PainView(Context context) {
        super(context);
    }

    public PainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         p = new Paint();
        p.setColor(Color.GREEN);//画笔设置绿色
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.parseColor("#1FF421"));
        p.setAntiAlias(true);
        p.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        for (int d = i +10; d < oldpint.length; d++) {//绘制上次绘制的波
            canvas.drawLine(oldpint[d][0],oldpint[d][1],oldpint[d][2],oldpint[d][3],p);
        }
        mHandler.postDelayed(r, 4);//定时器


        for (int j = 0; j < i; j++) {

            float x = (float) Sin(j);//获取起始点y值
            float y = (float) Sin(j + 1);//获取结束点y值

            if (j == getWidth() || i == 0) {//到达边界后返回
                j = 0;
                i = 0;
            }

            //保存上次绘图坐标
            oldpint [i][0] = j;
            oldpint [i][1] = x+50;
            oldpint [i][2] = j+1;
            oldpint [i][3] = y+50;


            canvas.drawRect(i , 0, i +10, getHeight(), mPaint);//绘制刷新黑框
            canvas.drawLine(j, x + 50, j + 1, y + 50, p);//绘制波形
            }
        }

        //正弦函数
    public double Sin(int i) {

        double result = 0;
//result = Math.cos(i * Math.PI / 180);
        result =20* Math.sin((i) * Math.PI / 180)+100;
//result = Math.tan(i * Math.PI / 180);
        return result;
    }

    //定时器回掉
    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {
            //每隔1s循环执行run方法
            i+= 4;
            n+=1;
//postInvalidate();
invalidate();
        }
    };

}