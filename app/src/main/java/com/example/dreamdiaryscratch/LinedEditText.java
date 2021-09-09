package com.example.dreamdiaryscratch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/// Used for adding underlines to EditTexts
///
/// Courtesy of old Android Developer sample resources, not available anymore but found on
/// https://stackoverflow.com/questions/4114859/android-edittext-underline

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private final Rect mRect;
    private final Paint mPaint;

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(0x800000FF);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = getLineCount();
        Rect r = mRect;
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, r);
            canvas.drawLine(r.left, baseline + 10, r.right, baseline + 10, mPaint);
        }
        super.onDraw(canvas);
    }
}