package com.unicorn.aems;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class FingerView extends View {

    public FingerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private int thick =2;
    private final RectF oval = new RectF();
    private final RectF oval2 = new RectF();

    private void updateOval() {
        float distance  = 50;
        float delta = thick / 2;
        oval.set(delta, delta, getWidth() - delta, getHeight() - delta);
        oval2.set(delta+distance,delta+distance,getWidth()-delta-distance,getHeight()-distance);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateOval();

    }
    Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);

    private void init(){
        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(thick);

    }

    private float startDegree = 0;
    private float startDegree2 = 45;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        canvas.drawArc(oval,startDegree,80,false,paint);
        canvas.drawArc(oval,startDegree+90,80,false,paint);
        canvas.drawArc(oval,startDegree+180,80,false,paint);
        canvas.drawArc(oval,startDegree+270,80,false,paint);

        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        canvas.drawArc(oval2,startDegree2,80,false,paint);
        canvas.drawArc(oval2,startDegree2+90,80,false,paint);
        canvas.drawArc(oval2,startDegree2+180,80,false,paint);
        canvas.drawArc(oval2,startDegree2+270,80,false,paint);
    }



    public void setStartDegree(float startDegree) {
        this.startDegree = startDegree;
        invalidate();
    }

    public void setStartDegree2(float startDegree2) {
        this.startDegree2 = startDegree2;
        invalidate();
    }

}
