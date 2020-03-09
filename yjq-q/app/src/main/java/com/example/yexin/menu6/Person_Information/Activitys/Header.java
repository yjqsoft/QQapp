package com.example.yexin.menu6.Person_Information.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Shear.Shear;
import com.example.yexin.menu6.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * Created by DELL on 2019/11/17.
 */
public class Header extends Activity {
    private ImageView mCorpImg=null;
    private RelativeLayout relativeLayout=null;
    private int top, left;
    private float downX, downY;
    private final int ZOOM = 1, DRAG = 0;
    private float oldSpacing = 1f;
    private int state;
    private PointF mid = new PointF();
    private boolean isMain, isPointer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userheader);
//        relativeLayout=(RelativeLayout)findViewById(R.id.picture);
        mCorpImg = (ImageView) findViewById(R.id.header);
        try{
            FileInputStream fis = new FileInputStream(UserPublic.getIcon());
            Bitmap mCropBitmap  = BitmapFactory.decodeStream(fis);
            mCorpImg.setImageBitmap(mCropBitmap);
        }catch(Exception e){
            e.printStackTrace();
        }

//        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams)imageView.getLayoutParams();//获取当前控件的布局对象
//        params.height=width;//设置当前控件布局的高度
//        imageView.setLayoutParams(params);//将设置好的布局参数应用到控件中
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Intent intent1 = new Intent(Header.this, PersonInformationActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.iv_dot_menu:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
                break;
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            //查询我们需要的数据
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);  //图片的路径可以用来剪切
            cursor.close();
            Intent intent = new Intent(Header.this, Shear.class);
            intent.putExtra("picture", picturePath);
            startActivityForResult(intent,2);
        }
        else if(requestCode == 2 && resultCode == 2 && null != data){
            try{
                String picture=data.getSerializableExtra("path").toString();
                Log.e("picture","图片的格式"+picture);
                FileInputStream fis = new FileInputStream(picture);
                Bitmap mCropBitmap  = BitmapFactory.decodeStream(fis);
                mCorpImg.setImageBitmap(mCropBitmap);
                //图片上传模块
                RequestParams params = new RequestParams("http://192.168.137.1:8080/ByteSoft_two/test");
                params.setMultipart(true);
                params.addBodyParameter("file",new File(picture));
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(Header.this, "更换成功", Toast.LENGTH_SHORT).show();
                        Log.e("001","头像上传成功");
                        //UserPublic.setIcon(picture);
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("002","失败了");
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.e("003","取消了");
                    }
                    @Override
                    public void onFinished() {
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//            Intent intent1=getIntent();
//            String picture=intent1.getStringExtra("path");
//            try{
//                FileInputStream fis = new FileInputStream(picture);
//                Bitmap mCropBitmap  = BitmapFactory.decodeStream(fis);
//                imageView.setImageBitmap(mCropBitmap);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            //拿到了图片的路径picturePath可以自行使用
//            Bitmap bitmap=BitmapFactory.decodeStream(getClass().getResourceAsStream(picturePath));
//            imageView.setImageBitmap(bitmap);
//            try{
//                FileInputStream fis = new FileInputStream(picturePath);
//                Bitmap bitmap  = BitmapFactory.decodeStream(fis);
//                imageView.setImageBitmap(bitmap);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//            ImageOptions options = new ImageOptions.Builder().setSize(240,240).build();
//            x.image().bind(imageView, picturePath,options);

//        switch (requestCode) {
//            case :
//                if (resultCode == RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        handleImageOnkitKat(data);//高于4.4版本使用此方法处理图片
//                    } else {
//                        handleImageBeforeKitKat(data);//低于4.4版本使用此方法处理图片
//                    }
//                }
//                break;
//            default:
//                break;
//        }
    }
}
