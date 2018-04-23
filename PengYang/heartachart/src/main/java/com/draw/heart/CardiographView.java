package com.draw.heart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by weipenghui on 2018/3/6.
 * use to chart  Electrocardiogram (ecg)
 * author：wph
 * @data: 2018-03-08 am 10:00
 *网格背景
 */

public class CardiographView extends View {

    //画笔
protected Paint mPaint;
//折线颜色
//protected int mLineColor = Color.parseColor("#76f112");
protected int mLineColor = Color.parseColor("#000000");
//网格颜色
//    protected int mBackColor = Color.parseColor(#F9CCE2);
    protected int mGridColor = Color.parseColor("#ff0000");
//    小网格颜色
protected int mSGridColor =  Color.parseColor("#ffb4b4");

//背景颜色
    protected  int mBackgroundColor = Color.BLACK;

//    自身大小
protected  int mWidth,mHeight;
//网格宽度
    protected float mGridWidth;
//    网格高度
protected float mGridHeight;




public CardiographView(Context context){
    this(context,null);
}

    public CardiographView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

public CardiographView(Context context, AttributeSet attrs){
    super(context, attrs,0);
    mPaint = new Paint();
    DisplayMetrics dm = new DisplayMetrics();
    dm = this.getResources().getDisplayMetrics();
    int dpi = dm.densityDpi;
//    Log.i("vvv","----dpi-"+dpi+"+++"+dm.widthPixels+"*"+dm.heightPixels);
//    网格的宽度  dpi  /25.4 = 1mm   在不同的安卓手机会有误差
    mGridWidth = (float) ((dpi / 25.4)*5);



//    网格高度
    mGridHeight = mGridWidth;
//            (float)((dpi / 25.4)*5);

}

protected void onSizeChanged(int w,int h,int oldw,int oldh){

    mWidth = w;
    mHeight = h;
    super.onSizeChanged(w,h,oldw,oldh);


}

public void onDraw(Canvas canvas){

//    Log.i("ononon","onDraw方法");
    initBackground(canvas);
    super.onDraw(canvas);

}

@SuppressLint("ResourceAsColor")
public void initBackground(Canvas canvas){

canvas.drawColor(mBackgroundColor);
//    RectF rect = new RectF(0,mGridHeight*6,mWidth,mGridHeight*11);
//    Log.e("网格宽度", String.valueOf(mGridWidth)+"-----"+String.valueOf(mGridHeight));
//    canvas.drawRect(rect,mPaint);
//    大网格竖线个数
int vNum = (int) (mWidth/mGridWidth);

int snum = (int) (mWidth/mGridWidth)*5;
//     横线个数
    int shNum = (int) (mHeight/mGridHeight*5*5);
    int hNum = (int) (mHeight/mGridHeight*5);
//高度差
    int a = (int) ((mHeight-(mGridHeight/5*(shNum+1)-2*mGridHeight/5))/2);
    //小格
    mPaint.setColor(Color.parseColor("#404040"));
    mPaint.setStrokeWidth(1);

    for(int sh = 0; sh<shNum+1; sh++){
        canvas.drawLine(mGridHeight/5,sh*mGridHeight/5+mGridHeight/5,mGridWidth/5*(snum+1),sh*mGridHeight/5+mGridHeight/5,mPaint);
    }

    for (int z = 0;z < snum+1;z++){
        canvas.drawLine((z*mGridWidth)/5+mGridHeight/5,mGridHeight/5,(z*mGridWidth)/5+mGridHeight/5,mGridHeight/5*(shNum),mPaint);
    }


//    大格
    mPaint.setColor(Color.parseColor("#838383"));
    mPaint.setStrokeWidth(2);
    mPaint.setTextSize(25);
//  画竖线
    for (int i  = 0;i < vNum+1;i++){
        canvas.drawLine(i*mGridWidth+mGridHeight/5,mGridHeight/5,i*mGridWidth+mGridHeight/5,mGridHeight/5*(shNum),mPaint);
    }
//画横线
for(int j = 0; j<hNum+1; j++){
        canvas.drawLine(mGridHeight/5,j*mGridHeight+mGridHeight/5,mGridWidth/5*(snum+1),j*mGridHeight+mGridHeight/5,mPaint);
}



}




}
