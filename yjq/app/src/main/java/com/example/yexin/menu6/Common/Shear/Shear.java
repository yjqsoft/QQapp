package com.example.yexin.menu6.Common.Shear;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DELL on 2019/11/17.
 */
public class Shear extends Activity {
    private TextView mCropBtn;
    private View mCropView;
    private ImageView mCorpImg;
    private RelativeLayout mCropLayout;
    private Bitmap mCropBitmap;
    private int top, left;
    private float downX, downY;
    private final int ZOOM = 1, DRAG = 0;
    private float oldSpacing = 1f;
    private int state;
    /**
     * 记录缩放时两指中间点坐标
     */
    private PointF mid = new PointF();
    private boolean isMain, isPointer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shear);
        mCropBtn = (TextView) findViewById(R.id.corp_handle_btn);
        mCropView = findViewById(R.id.crop_handler_select);
        mCorpImg = (ImageView) findViewById(R.id.crop_handler_img);
        mCropLayout = (RelativeLayout) findViewById(R.id.crop_handler_layout);
        Intent intent=getIntent();
        String picture=intent.getStringExtra("picture");
        try{
            FileInputStream fis = new FileInputStream(picture);
            mCropBitmap  = BitmapFactory.decodeStream(fis);
            mCorpImg.setImageBitmap(mCropBitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //要裁剪的图片
//        mCropBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.crop);
//        mCorpImg.setImageBitmap(mCropBitmap);

        mCorpImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //次要点被点击
                        oldSpacing = spacing(motionEvent);
                        if (oldSpacing > 10f) {
                            isPointer = false;
                            isMain = false;
                            state = ZOOM;
                            midPoint(mid, motionEvent);
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        //次要点松开
                        Log.d("CropHandlerActivity", "ACTION_POINTER_UP");
                        isPointer = true;
                        if (isPointer && isMain) {
                            isPointer = false;
                            isMain = false;
                            state = DRAG;
                            oldSpacing = 1f;
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX();
                        downY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Matrix matrix = mCorpImg.getImageMatrix();
                        if (state == DRAG) {
                            //拖拽
                            matrix.postTranslate(motionEvent.getX() - downX, motionEvent.getY() - downY);
                            downX = motionEvent.getX();
                            downY = motionEvent.getY();
                        } else if (state == ZOOM && !isPointer && !isMain) {
                            //放大缩小
                            float newSpacing = spacing(motionEvent);
                            float scale = newSpacing / oldSpacing;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                            oldSpacing = newSpacing;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isMain = true;
                        if (isPointer && isMain) {
                            isPointer = false;
                            isMain = false;
                            state = DRAG;
                            oldSpacing = 1f;
                        }
                        break;
                }
                mCorpImg.invalidate();
                return true;
            }
        });
    }


    /**
     * 多点触控时，计算最先放下的两指距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 多点触控时，计算最先放下的两指中心坐标
     *
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int layoutWidth = mCropLayout.getWidth();
            int layoutHeight = mCropLayout.getHeight();
            int imgWidth = mCropBitmap.getWidth();
            int imgHeight = mCropBitmap.getHeight();
            int selectWidth = mCropView.getWidth();
            int selectHeight = mCropView.getHeight();

            //缩放比例
            float scaleNum;

            //将要裁剪的图片长宽高做对比， 将较小的一方做等比缩放成裁剪框大小
            if (imgWidth < imgHeight) {
                scaleNum = (selectWidth * 1.0f) / (imgWidth * 1.0f);
                imgHeight = (int) (scaleNum * imgHeight);
                imgWidth = selectWidth;
            } else {
                scaleNum = (selectHeight * 1.0f) / (imgHeight * 1.0f);
                imgWidth = (int) (scaleNum * imgWidth);
                imgHeight = selectHeight;
            }

            Matrix matrix = new Matrix();
            matrix.postScale(scaleNum, scaleNum);
            //平移距离
            matrix.postTranslate((layoutWidth - imgWidth) / 2, (layoutHeight - imgHeight) / 2);

            top = (layoutHeight - selectHeight) / 2;
            left = (layoutWidth - selectWidth) / 2;

            //设置缩放类型为 矩阵
            mCorpImg.setScaleType(ImageView.ScaleType.MATRIX);
            mCorpImg.setImageMatrix(matrix);
            mCorpImg.setImageBitmap(mCropBitmap);
        }
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.corp_handle_btn:
//                String path=getCacheDir().toString();
                String path=SavePicture();
                Intent intent=new Intent();
                intent.putExtra("path",path);
                setResult(2,intent);
                finish();
//                CropShowImg.startThis(this, file.getAbsolutePath());
                break;
        }
    }
    /**
     * 获取裁剪框内截图
     *
     * @return
     */
    private Bitmap getBitmap() {
        // 获取截屏
        mCropLayout.setDrawingCacheEnabled(true);
        mCropLayout.buildDrawingCache();
        int borderWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        Bitmap finalBitmap = Bitmap.createBitmap(mCropLayout.getDrawingCache(),
                left + borderWidth, top + borderWidth, mCropView.getWidth() - 2 * borderWidth,
                mCropView.getHeight() - 2 * borderWidth);

        // 释放资源
        mCropLayout.destroyDrawingCache();
        return finalBitmap;
    }

    /**
     * 保存图片，保存到一个本地的文件夹，这里保存是要在公共类中保存地址，连接网络后，如果保存成功以后，
     * 发送到服务端，图片个命名格式是（用户的id加上.png）保存到data文件夹下面，这个文件夹在第一次登入就已经创建好了
     * 就用来保存该用户的头像。
     * @return
     */
    private String SavePicture(){
        String sdPath = Environment.getExternalStorageDirectory().getPath();
        try{
            File file=new File(sdPath+"/yjq");
            if (!file.exists()) {
                file.mkdirs();
                Log.e("1234","文件存在");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.e("qew",sdPath);
        Bitmap bitmap = getBitmap();
        FileOutputStream fos = null;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
        String fileName = "cropTemp_" + timeStamp + ".png";
        File file = new File(sdPath+ "/yjq/" + File.separator + fileName);
        Log.e("1234",file.toString());
        try {
        if (!file.exists()) {
            file.createNewFile();
        }

        fos = new FileOutputStream(file);
        if (fos != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return file.toString();
    }
}
