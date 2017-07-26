package com.binary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bestotem on 2017/7/16.
 */

public class DrawView extends View {

    private Path path = new Path();
    private float mPreX, mPreY;

    public DrawView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                path.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();

                return true;
            }
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;

                path.quadTo(mPreX, mPreY, endX, endY);
                mPreX = endX;
                mPreY = endY;

                invalidate();
                break;

            default:
                break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);


        canvas.drawPath(path, paint);
    }


}
