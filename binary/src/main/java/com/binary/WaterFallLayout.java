package com.binary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bestotem on 2017/7/17.
 */

public class WaterFallLayout extends ViewGroup {

    private int columns = 3;
    private int hSpace = 20;
    private int vSpace = 20;
    private int childWidth = 0;
    private int top[];
    private int minHeightColum;
    private int maxHeight;


    public WaterFallLayout(Context context) {
        this(context, null);
    }

    public WaterFallLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        top = new int[columns];

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        childWidth = (widthSize - (columns - 1) * hSpace) / columns;

        int wrapWidth;
        int childCount = getChildCount();
        if (childCount < columns) {
            wrapWidth = childCount * childWidth + (childCount - 1) * hSpace;
        } else {
            wrapWidth = widthSize;
        }

        clearTop();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            int minColum = getMinHeightColum();
            top[minColum] += vSpace + childHeight;
        }

        int wrapHeight;
        wrapHeight = getMaxHeight();
        setMeasuredDimension(widthMode == MeasureSpec.AT_MOST ? wrapWidth : widthSize, wrapHeight);

    }


    public int getMinHeightColum() {
        int minColum = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] < top[columns]) {
                minColum = i;
            }
        }
        return minColum;
    }


    public int getMaxHeight() {
        int maxHeight = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] > maxHeight) {
                maxHeight = top[i];
            }
        }

        return maxHeight;
    }

    private void clearTop() {
        for (int i = 0; i < columns; i++) {
            top[i] = 0;
        }
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();
        clearTop();
        for (int j = 0; j < childCount; j++) {
            View childView = this.getChildAt(i);
            int childHeight = childView.getMeasuredHeight() * childWidth / childView.getMeasuredWidth();
            int minColum = getMinHeightColum();
            int tLeft = minColum * (childWidth + hSpace);
            int tTop = top[minColum];
            int tRight = tLeft + childWidth;
            int tBottom = tTop + childHeight;

            top[minColum] += vSpace + childHeight;
            childView.layout(tLeft, tTop, tRight, tBottom);


        }

    }


    public interface OnItemClickListener {
        void onItemClick(View view, int index);
    }


    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, index);
                }
            });

        }
    }


}
