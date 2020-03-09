package com.example.yexin.menu6.Sideslip.Sideslip_top.Head_photo;

/**
 * Created by 邱 on 2019/8/21.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by joe.wang on 2016/9/12.
 */
public class PhotoImageView extends AppCompatImageView {

    private int mWdith;
    private int mHeight;
    private Paint mPaint;
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private Rect mDrawableRect;
    private Matrix mShaderMartix;

    public PhotoImageView(Context context) {
        this(context, null);
    }

    public PhotoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mShaderMartix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWdith = w;
        mHeight = h;
        fillDrawableRect();
        setup();
    }

    private void setup() {
        if (mBitmap == null) {
            getDrawableBitmap();
        }
    }

    /**
     * 获取当前设置的android:src属性中的图片bitmap
     */
    private void getDrawableBitmap() {
        if (getDrawable() == null) {
            return;
        } else {
            Drawable d = getDrawable();
            Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            d.draw(canvas);
            mBitmap = bitmap;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            super.onDraw(canvas);
        } else {
            getDrawableBitmap();
            mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            updateShaderMatrix();
            mPaint.setShader(mBitmapShader);
            canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRect.width() / 2, mPaint);
        }
    }

    /**
     * 获取一个正方形，用于绘制圆形
     */
    private void fillDrawableRect() {
        int avaliableWidth = mWdith - getPaddingLeft() - getPaddingRight();
        int avaliableHeight = mHeight - getPaddingTop() - getPaddingBottom();
        int slideLength = Math.min(avaliableWidth, avaliableHeight);
        int top = getPaddingTop() + (avaliableHeight - slideLength) / 2;
        int left = getPaddingLeft() + (avaliableWidth - slideLength) / 2;
        mDrawableRect = new Rect(left, top, left + slideLength, top + slideLength);
    }

    /**
     * 根据不同的比例大小设置bitmap的缩放大小和偏移量
     */
    private void updateShaderMatrix() {
        float scale = 0;
        int dx = 0;
        int dy = 0;
        if (mBitmap.getWidth() * mDrawableRect.height() > mBitmap.getHeight() * mDrawableRect.width()) {
            scale = mDrawableRect.width() * 1.0f / mBitmap.getWidth();
            dy = (int) ((mDrawableRect.height() - mBitmap.getHeight() * scale) * 0.5f);
        } else {
            scale = mDrawableRect.height() * 1.0f / mBitmap.getHeight();
            dx = (int) ((mDrawableRect.width() - mBitmap.getWidth() * scale) * 0.5f);
        }
        mShaderMartix.setScale(scale, scale);
        mShaderMartix.postTranslate(dx + mDrawableRect.left, dy + mDrawableRect.top);
        mBitmapShader.setLocalMatrix(mShaderMartix);
    }
}

