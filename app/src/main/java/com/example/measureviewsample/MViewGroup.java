package com.example.measureviewsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/*
 *  Created  in  2021/5/31
 */
public class MViewGroup extends ViewGroup {
    public MViewGroup(Context context) {
        super(context);
    }

    public MViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    //测量view大小带margin 必须用marginlayoutparams 要实现以下三个方法

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new MarginLayoutParams(this.getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View v1 = getChildAt(0);
        View v2 = getChildAt(1);

        measureChildWithMargins(v1, widthMeasureSpec,
                v1.getMeasuredWidth(), heightMeasureSpec, v1.getMeasuredHeight());

        measureChildWithMargins(v2, widthMeasureSpec,
                v2.getMeasuredWidth(), heightMeasureSpec, v2.getMeasuredHeight());

        int width = Math.max(v1.getMeasuredWidth(), v2.getMeasuredWidth());
        int height = v1.getMeasuredHeight() + v2.getMeasuredHeight();
        setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(),
                height + getPaddingTop() + getPaddingBottom()
        +((MarginLayoutParams) v2.getLayoutParams()).topMargin);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View v1 = getChildAt(0);
        View v2 = getChildAt(1);
        v1.layout(getPaddingLeft(),
                getPaddingTop(),
                getPaddingLeft() + getMeasuredWidth(),
                getPaddingTop() + v1.getMeasuredHeight());
        int marTop = ((MarginLayoutParams) v2.getLayoutParams()).topMargin;
        Log.i(TAG, "onLayout: "+marTop);
        v2.layout(getPaddingLeft(),
                v1.getBottom() +marTop,
                getPaddingLeft() + v2.getMeasuredWidth(),
                v1.getBottom() + v2.getMeasuredHeight()+marTop);
    }
    public static String TAG="aaaa";
}
