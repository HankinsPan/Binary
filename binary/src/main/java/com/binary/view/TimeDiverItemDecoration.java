package com.binary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.binary.R;

/**
 * Created by bestotem on 2017/7/27.
 */

/**
 * 自定义一个 ItemDecoration 通常要根据需要，复写它的 3 个方法。
 *
 *  1. getItemOffsets 撑开 ItemView 上、下、左、右四个方向的空间
 *  2. onDraw 在 ItemView 内容之下绘制图形
 *  3. onDrawOver 在 ItemView 内容之上绘制图形。
 */
public class TimeDiverItemDecoration extends RecyclerView.ItemDecoration {
    public static final String TAG = "TimeDiverItemDecoration";

    private float mOffsetLeft; // ItemView 左边的间距
    private float mOffsetTop;   // ItemView 上边的间距
    private float mNodeRadius; // 时间轴原点的半径
    private float mFlagLeft;
    private Bitmap mIcon;

    private Paint mPaint;

    public TimeDiverItemDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#0CCE6B"));
        mPaint.setStrokeWidth(3);

        mOffsetLeft = context.getResources().getDimensionPixelOffset(R.dimen.timeline_item_offset_left);
        mNodeRadius = context.getResources().getDimensionPixelOffset(R.dimen.timeline_item_node_radius);
        mFlagLeft = context.getResources().getDimensionPixelOffset(R.dimen.timeline_item_flag_left);
        mIcon = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_github);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top = 1;
            mOffsetTop = 1;
        }
        outRect.left = (int) mOffsetLeft;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            float driverTop = view.getTop() - mOffsetTop;

//            Log.e(TAG, "show me getTop is ->>>>>"+view.getTop());
//            Log.e(TAG, "show me mOffsetTop is ->>>>>"+mOffsetTop);

            if (index == 1) {
                driverTop = view.getTop();
            }

            float driverLeft = parent.getPaddingLeft();
            float driverBottom = view.getBottom();
            float driverRight = parent.getWidth() - parent.getPaddingRight();

//            Log.e(TAG, "  driverLeft-> "+driverLeft+"  driverBottom-> "+driverBottom+"  driverRight-> "+driverRight);

            float centerX = driverLeft + mOffsetLeft / 2;
            float centerY = driverTop + (driverBottom - driverTop) / 2;

            float upLineTopX = centerX;
            float upLineTopY = driverTop;
            float upLineBottomX = centerX;
            float upLineBottomY = centerY - mNodeRadius;
            // 绘制上半部分轴线
            c.drawLine(upLineTopX, upLineTopY, upLineBottomX, upLineBottomY, mPaint);


            // 绘制时间轴中心圆点
            mPaint.setStyle(Paint.Style.STROKE);
            c.drawCircle(centerX, centerY, mNodeRadius, mPaint);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            float downLineTopX = centerX;
            float downLineTopY = centerY + mNodeRadius;
            float downLineBottomX = centerX;
            float downLineBottomY = driverBottom;

            //绘制下半部分轴线
            c.drawLine(downLineTopX, downLineTopY, downLineBottomX, downLineBottomY, mPaint);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childNum = parent.getChildCount();

        for (int i = 0; i < childNum; i++) {
            View view = parent.getChildAt(i);
            int childIndex = parent.getChildAdapterPosition(view);
            float top = view.getTop();
            if (childIndex<5){
                c.drawBitmap(mIcon,mFlagLeft,top,mPaint);
            }
        }
    }
}
