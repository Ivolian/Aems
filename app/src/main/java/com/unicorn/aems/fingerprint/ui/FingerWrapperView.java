package com.unicorn.aems.fingerprint.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.unicorn.aems.R;

import butterknife.ButterKnife;

public class FingerWrapperView extends FrameLayout {

    public FingerWrapperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        ButterKnife.bind(this);
    }

    private final int outerWidth = ConvertUtils.dp2px(1);
    private final int innerWidth = ConvertUtils.dp2px(1);
    private final RectF outerOval = new RectF();
    private final RectF innerOval = new RectF();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateOval();
    }

    private void updateOval() {
        float distance = ConvertUtils.dp2px(16);
        float delta = outerWidth;
        outerOval.set(delta, delta, getWidth() - delta, getHeight() - delta);
        innerOval.set(delta + distance, delta + distance, getWidth() - delta - distance, getHeight() - distance);
    }

    Paint outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private void init() {
        setWillNotDraw(false);
        outerPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(outerWidth);
        innerPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeWidth(innerWidth);
    }

    private float outerStartDegree = -1;
    private float innerStartDegree = -1;
    private final float gapDegree = 15;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float sweepDegree = 90 - gapDegree;

        if (outerStartDegree == -1 || innerStartDegree == -1) {
            return;
        }

        canvas.drawArc(outerOval, outerStartDegree, sweepDegree, false, outerPaint);
        canvas.drawArc(outerOval, outerStartDegree + 90, sweepDegree, false, outerPaint);
        canvas.drawArc(outerOval, outerStartDegree + 180, sweepDegree, false, outerPaint);
        canvas.drawArc(outerOval, outerStartDegree + 270, sweepDegree, false, outerPaint);

        canvas.drawArc(innerOval, innerStartDegree, sweepDegree, false, innerPaint);
        canvas.drawArc(innerOval, innerStartDegree + 90, sweepDegree, false, innerPaint);
        canvas.drawArc(innerOval, innerStartDegree + 180, sweepDegree, false, innerPaint);
        canvas.drawArc(innerOval, innerStartDegree + 270, sweepDegree, false, innerPaint);
    }

    public void setOuterStartDegree(float outerStartDegree) {
        this.outerStartDegree = outerStartDegree;
        invalidate();
    }

    public void setInnerStartDegree(float innerStartDegree) {
        this.innerStartDegree = innerStartDegree;
        invalidate();
    }

}
