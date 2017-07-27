package com.binary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binary.R;

/**
 * Created by bestotem on 2017/7/27.
 */

public class TimeDiverItemDecoration extends RecyclerView.ItemDecoration {

    private float mDividerHeight;
    private float mOffsetLeft;
    private float mOffsetTop;
    private float mNodeRadius;

    private Paint mPaint;

    public TimeDiverItemDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mOffsetLeft = context.getResources().getDimensionPixelOffset(R.dimen.timeline_item_offset_left);
        mNodeRadius = context.getResources().getDimensionPixelOffset(R.dimen.timeline_item_node_radius);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top = 2;
            mDividerHeight = 2;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            if (index == 0) {
                continue;
            }

            float driverTop = view.getTop() - mDividerHeight;
            float driverBottom = view.getTop();
            float driverLeft = parent.getPaddingLeft();
            float driverRight = parent.getWidth() - parent.getPaddingRight();

            c.drawRect(driverLeft, driverTop, driverRight, driverBottom, mPaint);
        }

    }
}
