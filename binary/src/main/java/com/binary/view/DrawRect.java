package com.binary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by bestotem on 2017/7/16.
 */

public class DrawRect extends View {

    private Path mPath;
    private Paint paint;
    private int mItemWaveLength = 400;
    private int dx;


    public DrawRect(Context context) {
        super(context);
    }

    public DrawRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.LTGRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        int originY = 300;
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i < getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen / 2, -50, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, 50, halfWaveLen, 0);
        }
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();


        canvas.drawPath(mPath, paint);

//        Path path = new Path();
//        path.moveTo(100, 300);
//        path.quadTo(200, 200, 300, 300);
//        path.quadTo(400, 400, 500, 300);
//        path.rQuadTo(100,-100,200,0);
//        path.rQuadTo(100,100,100,0);

//        RectF rectF = new RectF(100,10,300,300);
//        Path path = new Path();
//        path.addCircle(150,150,120, Path.Direction.CCW);
        // 限定区域大小 l,t,r,b

//        Region region = new Region(10,10,100,100);
//        drawRegin(canvas,region,paint);

//        canvas.drawLine(baseLineX,baseLineY,3000,baseLineY,paint);
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(50);
//        canvas.drawText("hankins",baseLineX,baseLineY,paint);

//        canvas.drawRoundRect(rectF,20,10,paint);
//        canvas.drawPath(path,paint);
    }

//    private void drawRegin(Canvas canvas, Region region, Paint paint) {
//        RegionIterator iterator = new RegionIterator(region);
//        Rect rect = new Rect();
//
//        while (iterator.next(rect)) {
//            canvas.translate(100, 100);
//            canvas.drawRect(rect, paint);
//        }
//    }

    public void startAnim() {
        final ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(800);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();

            }
        });
        animator.start();
    }

}
