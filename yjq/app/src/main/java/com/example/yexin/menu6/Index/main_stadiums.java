package com.example.yexin.menu6.Index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

//import static com.baidu.location.h.k.R;

public class main_stadiums extends Activity implements OnBannerListener {
    //mData,mContext,mAdapter,fragmentone_select_listview 演示数据，界面，数据适配，列表
    private LinkedList<stadiums_balls> mData=null;
    private LinkedList<userinfo_adapter> mDate2=null;
    private Context mContext;
    private stadiums_balls_adapter mAdapter = null;
    private stadiums_evalutes_adapter mAdapter2=null;
    private ListView list_ballsselect,list_user_evalutes;
    private ArrayList<Integer> imagePath=null;
    private int positionInt;

    private TextView ballclub_name1,address,distance,facility,server;
    private ImageView stadium_location;
    public static LinkedList<SearchReasult> mDatainformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stadiums);
        mContext=main_stadiums.this;

        init();//初始化变量


        /*取出场馆编号*/
        Intent intent =getIntent();
        String  positionString=intent.getStringExtra("position");
        positionInt=Integer.parseInt(positionString);
//        Log.e("weizhi","位置"+positionInt);
//        Log.e("weizhi","编号"+mDatainformation.get(positionInt).getNo());
        Log.e("weizhi","球类型"+mDatainformation.get(positionInt).getBall());

      //  Log.e("weizhi","变量"+ballclub_name1.toString());
        ballclub_name1.setText(mDatainformation.get(positionInt).getBallclub_name());
        address.setText(mDatainformation.get(positionInt).getAddress());
        distance.setText(mDatainformation.get(positionInt).getDistance());
        facility.setText("   "+mDatainformation.get(positionInt).getIntroduce());//场馆介绍
        server.setText("   "+mDatainformation.get(positionInt).getServer());


        //评论适配器
        mDate2=new LinkedList<userinfo_adapter>();
        mDate2.add(new userinfo_adapter("阿亮",""+positionInt));
        mDate2.add(new userinfo_adapter("阿亮","不错，我很喜欢"));
        mDate2.add(new userinfo_adapter("阿亮","不错，我很喜欢"));
        mAdapter2=new stadiums_evalutes_adapter(mDate2,mContext);
        list_user_evalutes.setAdapter(mAdapter2);
        //球类适配器
        mData=new LinkedList<stadiums_balls>();
        if(mDatainformation.get(positionInt).getBall().indexOf("A")!=-1)
            mData.add(new stadiums_balls("羽毛球","￥100"));
        if (mDatainformation.get(positionInt).getBall().indexOf("B")!=-1)
            mData.add(new stadiums_balls("足球","￥100"));
        if (mDatainformation.get(positionInt).getBall().indexOf("C")!=-1)
            mData.add(new stadiums_balls("网球","￥100"));
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

        //监听器
        stadium_location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent Mapintent=new Intent(main_stadiums.this, Stadium_location_show.class);
                Mapintent.putExtra("address",mDatainformation.get(positionInt).getAddress());
                //Intent myintent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(Mapintent);
                finish();

            }
        });

    }

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
        //定位图标
        stadium_location=(ImageView)findViewById(R.id.stadium_location);


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
}
