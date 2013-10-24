package com.xgc1986.ParallaxView;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ParallaxTransformer implements ViewPager.PageTransformer {

    private static float MIN_SCALE = 0.85f;
    private static float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {

        RelativeLayout root = (RelativeLayout) ((FrameLayout) view).getChildAt(0);

        ImageView imageView = (ImageView)root.getChildAt(0);

        if (position < -1) {
           // view.setAlpha(0);
        } else if (position <= 1) {

            Matrix matrix = new Matrix();
            matrix.reset();

            float wv = imageView.getWidth();
            float hv = imageView.getHeight();

            float wi = imageView.getDrawable().getIntrinsicWidth();
            float hi = imageView.getDrawable().getIntrinsicHeight();

            float width = wv;
            float height = hv;

            if (wi / wv > hi / hv) {
                matrix.setScale(hv / hi, hv / hi);
                width = wi * hv / hi;
            } else {
                matrix.setScale(wv / wi, wv / wi);
                height= hi * wv / wi;
            }

            matrix.preTranslate(- (position * width * 0.2f) + (wv - width) / 2, (hv - height) / 2);
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            imageView.setImageMatrix(matrix);
            root.setScaleX(0.99f);
            root.setScaleY(0.99f);
        } else {
           // view.setAlpha(0);
        }
    }
}