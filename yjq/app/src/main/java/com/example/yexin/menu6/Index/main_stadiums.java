package com.example.yexin.menu6.Index;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yexin.menu6.Index.BaiduMap.Stadium_location_show;
import com.example.yexin.menu6.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class main_stadiums extends Activity implements OnBannerListener {
    //mData,mContext,mAdapter,fragmentone_select_listview 演示数据，界面，数据适配，列表
    private LinkedList<stadiums_balls> mData=null;
    private LinkedList<userinfo_adapter> mDate2=null;
    private ArrayList<Integer> imagePath=null;
    public static LinkedList<SearchReasult> mDatainformation;

    private Context mContext;

    private stadiums_balls_adapter mAdapter = null;
    private stadiums_evalutes_adapter mAdapter2=null;

    private ListView list_ballsselect,list_user_evalutes;
    private TextView ballclub_name1,address,distance,facility,server;
    private ImageView iv_stadium_location,iv_stadium_phone,ball_collect,back;


    private int positionInt;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stadiums);
        mContext=main_stadiums.this;

        init();//初始化变量

        /*取出场馆编号*/
        Intent intent =getIntent();
        String  positionString=intent.getStringExtra("position");
        phone=intent.getStringExtra("phone");
        positionInt=Integer.parseInt(positionString);

        Log.e("weizhi","球类型"+mDatainformation.get(positionInt).getBall());
        ballclub_name1.setText(mDatainformation.get(positionInt).getBallclub_name());
        address.setText(mDatainformation.get(positionInt).getAddress());
        distance.setText(mDatainformation.get(positionInt).getDistance());
        facility.setText("   "+mDatainformation.get(positionInt).getIntroduce());//场馆介绍
        server.setText("   "+mDatainformation.get(positionInt).getServer());

        //评论适配器
        mDate2=new LinkedList<userinfo_adapter>();
       // mDate2.add(new userinfo_adapter("阿亮",""+positionInt));
        mDate2.add(new userinfo_adapter("阿亮","不错，我很喜欢"));
        mDate2.add(new userinfo_adapter("阿亮","不错，我很喜欢"));
        mAdapter2=new stadiums_evalutes_adapter(mDate2,mContext);
        list_user_evalutes.setAdapter(mAdapter2);
        //球类适配器
        mData=new LinkedList<stadiums_balls>();
        if(mDatainformation.get(positionInt).getBall().indexOf("A")!=-1)
            mData.add(new stadiums_balls("羽毛球","￥100",mDatainformation.get(positionInt).getNo(),mDatainformation.get(positionInt).getBallclub_name()));
        if (mDatainformation.get(positionInt).getBall().indexOf("B")!=-1)
            mData.add(new stadiums_balls("足球","￥100",mDatainformation.get(positionInt).getNo(),mDatainformation.get(positionInt).getBallclub_name()));
        if (mDatainformation.get(positionInt).getBall().indexOf("C")!=-1)
            mData.add(new stadiums_balls("网球","￥100",mDatainformation.get(positionInt).getNo(),mDatainformation.get(positionInt).getBallclub_name()));
        mAdapter=new stadiums_balls_adapter(mData,mContext);
        list_ballsselect.setAdapter(mAdapter);
        Banner banner=(Banner)findViewById(R.id.stadiums_banner);
        initDate_banner();
        //设置图片加载器
        banner.setImageLoader(new main_stadiums.GlideImageLoader());
        //设置图片集合
        banner.setImages(imagePath);
        //设置监听
        banner.setOnBannerListener(this);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(imageTitle);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.isAutoPlay(true);
        //start必须放在最后
        banner.start();

        //设置地图图标的监听器  将坐标和地址传入**12138
        iv_stadium_location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent Mapintent=new Intent(main_stadiums.this, Stadium_location_show.class);
                Mapintent.putExtra("address",mDatainformation.get(positionInt).getAddress());
                Mapintent.putExtra("location",mDatainformation.get(positionInt).getLocation());
                //Intent myintent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(Mapintent);
                finish();

            }
        });
        iv_stadium_phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkPermission(main_stadiums.this);
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(main_stadiums.this, "没有商家电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentcall = new Intent();
                    intentcall.setAction(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + phone);
                    intentcall.setData(data);
                    startActivity(intentcall);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(main_stadiums.this,  MainActivity.class);
                startActivity(intent_back);
                finish();
            }
        });
        ball_collect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * 功能未实现
                 * */
                //获取用户id  球馆id mDatainformation.get(positionInt).getNo()，球馆名mDatainformation.get(positionInt).getBallclub_name()
                //加入到数据库表中去
                //每次点击 状态为 收藏/不收藏
                Toast.makeText(main_stadiums.this,"收藏"+mDatainformation.get(positionInt).getNo()+" "+mDatainformation.get(positionInt).getBallclub_name(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    //将搜索界面的数传入该界面来 **12138  context留出空间 目前不使用
    public static void setmData(LinkedList<SearchReasult> mData, Context mContext) {
        mDatainformation=mData;
        //this.mContext=mContext;
    }

    private void init(){

        list_ballsselect=(ListView)findViewById(R.id.list_ballsselect);
        list_user_evalutes=(ListView)findViewById(R.id.list_user_evalutes);
        list_ballsselect=(ListView)findViewById(R.id.list_ballsselect);
        list_user_evalutes=(ListView)findViewById(R.id.list_user_evalutes);

        ballclub_name1=(TextView)findViewById(R.id.tv_ballclub_name1);
        address=(TextView)findViewById(R.id.tv_address);
        distance=(TextView)findViewById(R.id.tv_distance);
        facility=(TextView)findViewById(R.id.tv_facility);
        server=(TextView)findViewById(R.id.tv_server);
        iv_stadium_location=(ImageView)findViewById(R.id.iv_stadium_location);
        iv_stadium_phone=(ImageView)findViewById(R.id.iv_stadium_phone);
        ball_collect=(ImageView)findViewById(R.id.ball_collect);
        back=(ImageView)findViewById(R.id.back);


    }
    //初始数据banner
    private void initDate_banner(){
        imagePath=new ArrayList<>();
        imagePath.add(R.drawable.banner1);
        imagePath.add(R.drawable.banner2);
        imagePath.add(R.drawable.banner3);
    }
    @Override
    public void OnBannerClick(int position) {
        /*图片监听器*/
        switch (position){
            case 0:
                Toast.makeText(this,"第一个",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"第二个",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"第三个",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //重写图片加载器
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //权限检测  并打开权限
    private void checkPermission(Activity activity) {
        // Storage Permissions
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.CALL_PHONE,/*CALL_PHONE/*READ_EXTERNAL_STORAGE*/
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        try {
            //检测是否有拨号的权限
            int permission = ActivityCompat.checkSelfPermission(main_stadiums.this, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                // requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                ActivityCompat.requestPermissions(main_stadiums.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
