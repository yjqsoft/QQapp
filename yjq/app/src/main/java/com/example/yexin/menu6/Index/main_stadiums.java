package com.example.yexin.menu6.Index;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.BaiduMap.Stadium_location_show;
import com.example.yexin.menu6.Index.fragmenttwo_fourballs.fragmenttwo_balls_details;
import com.example.yexin.menu6.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class main_stadiums extends Activity implements OnBannerListener {
    //mData,mContext,mAdapter,fragmentone_select_listview 演示数据，界面，数据适配，列表
    private LinkedList<stadiums_balls> mData=null;
    private LinkedList<userinfo_adapter> mDate2=null;
    private ArrayList<Integer> imagePath=null;
    public static LinkedList<SearchReasult> mDatainformation;
    private static Context main_stadiumsContext;
    private Context mContext;

    private stadiums_balls_adapter mAdapter = null;
    private stadiums_evalutes_adapter mAdapter2=null;

    private ListView list_ballsselect,list_user_evalutes;
    private TextView ballclub_name1,address,distance,facility,server;
    private ImageView iv_stadium_location,iv_stadium_phone,ball_collect,back;
    private Button btn_day1,btn_day2,btn_day3;


    private int positionInt;
    public static int TimeStatus;
    private String phone;

    private SwipeRefreshLayout swipeRefreshView;

//    private android.support.v4.app.Fragment[] mFragmentArrays = new android.support.v4.app.Fragment[3];
//    private String[] mTabTitles = new String[3];
//    private TabLayout tabLayout = null;
//    private ViewPager viewPager;


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
            mData.add(new stadiums_balls("篮球","￥100",mDatainformation.get(positionInt).getNo(),mDatainformation.get(positionInt).getBallclub_name()));
        if (mDatainformation.get(positionInt).getBall().indexOf("C")!=-1)
            mData.add(new stadiums_balls("足球","￥100",mDatainformation.get(positionInt).getNo(),mDatainformation.get(positionInt).getBallclub_name()));
        if (mDatainformation.get(positionInt).getBall().indexOf("D")!=-1)
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
                //if()
                ball_collect.setImageDrawable (getResources().getDrawable(R.drawable.likeis));
                Toast.makeText(main_stadiums.this,"收藏"+mDatainformation.get(positionInt).getNo()+" "+
                        mDatainformation.get(positionInt).getBallclub_name(),Toast.LENGTH_SHORT).show();

                //开始  修改这里是注释 这段代码   这个是网络请求的
//                RequestParams params = new RequestParams(Web_url.URL_AddCollection);
//                params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//                params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//                JSONObject jsonObject=new JSONObject();
//                try{
//                    jsonObject.put("userId","18879942330");//获取用户的id名称
//                    jsonObject.put("gymnasiumId",mDatainformation.get(positionInt).getNo());//获取用户的id名称
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                 params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里
//                x.http().post(params, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
////               HashMap<String,String> map=Web_Json.getJson(result);
//                        //HashMap<String,String> map=Web_Json.getJson(result);
//                        Log.e("yjqresult:",result.toString());
//                        Toast.makeText(main_stadiums.this,"收藏"+result.toString(),Toast.LENGTH_SHORT).show();
//                   /*
//                   * 此处不能运行*/
//
//                    }
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        Log.e("yjq1","失败");
//                        Toast.makeText(mContext, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//                        Log.e("yjq","取消");
//                    }
//                    @Override
//                    public void onFinished() {
//                        Log.e("yjq","完成");
//                        //完成时候运行
//                    }
//                });
                //结束
            }
        });

        btn_day1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    btn_day1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    TimeStatus=0;//今天
                }else{
                    btn_day1.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        btn_day2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    btn_day2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    TimeStatus=1;//明天
                }else{
                    btn_day2.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
        btn_day3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    btn_day3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    TimeStatus=2;//后天
                }else{
                    btn_day3.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
    }


    private void pulldownRefresh() {
        swipeRefreshView.setRefreshing(false);
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                //final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mList.add(0, "我是天才" + random.nextInt(100) + "号");
//                        mAdapter.notifyDataSetChanged();
                        try {
                            //http();  在这可以执行一些耗时间的程序
                        } catch (Exception e) {
                        }
                        //Toast.makeText(mContext, "刷新了一条数据", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来

                    }
                }, 1200);
            }

        });
    }
    //将搜索界面的数传入该界面来 **12138  context留出空间 目前不使用
    public static void setmData(LinkedList<SearchReasult> mData, Context fragmentone_stadiums_adapterContext) {
        mDatainformation=mData;
       // main_stadiumsContext=fragmentone_stadiums_adapterContext;
    }

    private void init(){

        list_ballsselect=(ListView)findViewById(R.id.list_ballsselect);
        list_user_evalutes=(ListView)findViewById(R.id.list_user_evalutes);
        list_ballsselect=(ListView)findViewById(R.id.list_ballsselect);
        list_user_evalutes=(ListView)findViewById(R.id.list_user_evalutes);

        btn_day1=(Button)findViewById(R.id.btn_day1);
        btn_day2=(Button)findViewById(R.id.btn_day2);
        btn_day3=(Button)findViewById(R.id.btn_day3);
        btn_day1.setFocusableInTouchMode(true);
        btn_day2.setFocusableInTouchMode(true);
        btn_day3.setFocusableInTouchMode(true);

//        tabLayout = (TabLayout)findViewById(R.id.tablayout);
//        viewPager = (ViewPager)findViewById(R.id.tab_viewpager);

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
    //系统按键返回上一界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(main_stadiums.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
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
