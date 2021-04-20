package com.example.canvastesting.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.canvastesting.R;

public class CustomView extends View {

    private int mSquareX, mSquareY;
    private static final int SQUARE_SIZE_DEF = 100;
    private Rect mRectSquare;
    private Paint mPaintSquare;


    private int mSquareColor;
    private int mSquareSize;

    private Paint mPaintCircle;

    private float mCircleX, mCircleY;
    private float mCircleRadius = 100f;


    //null is necessary because the first constructor does not have the AttributeSet info, so the code needs to be optimized to not have the AttributeSet
    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);


        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor("#00ccff"));

        if( set == null ){
            return;
        }
        // parse the atributes files from values folder
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        mSquareColor = ta.getColor(R.styleable.CustomView_square_color, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size,SQUARE_SIZE_DEF );
        ta.recycle();
        mPaintSquare.setColor(mSquareColor);
    }

    public void animateObject() throws InterruptedException {

    // uses recursion
            if(mSquareX >= 500){
                postInvalidate();
            }else{
                delay(100000);
                System.out.println("+10");
                mSquareX = mSquareX + 10;
                postInvalidate();
                animateObject();
            }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mRectSquare.left = mSquareX;
        mRectSquare.top = mSquareY;
        mRectSquare.right = mRectSquare.left + mSquareSize;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;

        canvas.drawRect(mRectSquare, mPaintSquare);

        if(mCircleX == 0f || mCircleY == 0f){
            mCircleX = getWidth()/2;
            mCircleY = getHeight()/2;
        }


        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);
    }


    public boolean onTouchEvent(MotionEvent event){
        boolean value = super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();

                double dx = Math.pow( x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);

                if(dx + dy < Math.pow(mCircleRadius, 2)){
                    // Touched
                    mCircleY = y;
                    mCircleX = x;

                    postInvalidate();// need to call this anytime a change is made to the custom view

                    return true;
                }


            }
        }
        return value;
    }


    private void delay(long n) {
        n *= 1000;
        long startDelay = System.nanoTime();
        long endDelay = 0;
        while (endDelay - startDelay < n)
            endDelay = System.nanoTime();
    }
}
