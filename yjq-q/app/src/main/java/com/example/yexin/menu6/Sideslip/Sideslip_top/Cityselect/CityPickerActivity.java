package com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.adapter.CityListAdapter;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.adapter.ResultListAdapter;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean.AreasBean;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean.City;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean.CityPickerBean;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean.LocateState;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.util.GsonUtil;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.util.PinyinUtils;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.util.ReadAssetsFileUtil;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.widget.SideLetterBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by ye xin on 2019/10/13.
 */

public class CityPickerActivity extends FragmentActivity implements View.OnClickListener {
    private ListView mListView;
    private SideLetterBar mLetterBar;
    private CityListAdapter mCityAdapter;
    private ImageView clearBtn;
    private ViewGroup emptyView;
    private ListView mResultListView;
    private EditText searchBox;
    private ImageView search_clear;
    private ResultListAdapter mResultCityAdapter;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setSystemBarTransparent();
        setContentView(R.layout.cp_activity_city_list);

        initView();


        Log.w("base","initView_执行");
        initData();
        Log.w("base","initData_执行");
        getLocation();
        Log.w("base","initgetLo_执行");
    }
    protected void initView() {
        mListView = findViewById(R.id.listview_all_city);
        mResultListView=findViewById(R.id.search_result_listview);
        TextView overlay = findViewById(R.id.tv_letter_overlay);
        mLetterBar = findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });
        mCityAdapter = new CityListAdapter(this);

        mListView.setAdapter(mCityAdapter);



        //搜索框功能实现
        searchBox = (EditText) findViewById(R.id.et_search);
        search_clear=(ImageView)findViewById(R.id.iv_search_clear);

        search_clear.setOnClickListener(this);

        final String json_search = ReadAssetsFileUtil.getJson(this, "city.json");
         searchBox.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
             }

             @Override
             public void afterTextChanged(Editable s) {

                 HashSet<City> citys_search = new HashSet<>();
                 if (TextUtils.isEmpty(s)){
                     mResultListView.setVisibility(View.GONE);
                     search_clear.setVisibility(View.GONE);
                 }else  {

                     CityPickerBean bean = GsonUtil.getBean(json_search, CityPickerBean.class);

                     for (AreasBean areasBean : bean.data.areas) {
                         String name = areasBean.name.replace("", "");
                         for (AreasBean.ChildrenBeanX childrenBeanX : areasBean.children) {

                             Log.w("pingying市3","zazzhe  "+PinyinUtils.getPinYin(childrenBeanX.name).indexOf(s.toString().toLowerCase()));

                             if(PinyinUtils.getPinYin(childrenBeanX.name).indexOf(s.toString().toLowerCase())>-1 ||childrenBeanX.name.indexOf(s.toString().toLowerCase())>-1){
                                 int i=PinyinUtils.getPinYin(childrenBeanX.name).indexOf(s.toString().toLowerCase());
                                 int i2=childrenBeanX.name.indexOf(s.toString().toLowerCase());
                                 Log.w("pingying市3","zazzhe  "+i+"   "+i2);

                                 Log.w("pingying市2",childrenBeanX.name);
                                 citys_search.add(new City(childrenBeanX.name, PinyinUtils.getPinYin(childrenBeanX.name)));
                                 Log.w("pingying市2",new City(childrenBeanX.name, PinyinUtils.getPinYin(childrenBeanX.name)).toString());
                                 mResultListView.setVisibility(View.VISIBLE);
                             }
                         }
                     }

                     search_clear.setVisibility(View.VISIBLE);
                 }
                 if(s!=null){
                     ArrayList<City> cities_search = new ArrayList<>(citys_search);
                     mResultCityAdapter=new ResultListAdapter(CityPickerActivity.this,cities_search);
                     mResultListView.setAdapter(mResultCityAdapter);
                     // 运用接口返回搜索结果的城市  接收返回值 **12138
                     mResultCityAdapter.setOnResultClickListener(new ResultListAdapter.OnResultClickListener(){
                         @Override
                         public void onCityClick(String name) {
                             Intent intent_ityselect1 = getIntent();
                             intent_ityselect1.putExtra("city_name", name);
                             setResult(1, intent_ityselect1);
                             finish();
                         }
                     });
                 }

             }

         });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //输入框删除功能构造
            case R.id.iv_search_clear:
                searchBox.setText("");
                search_clear.setVisibility(View.GONE);
                break;
            //退出功能构造
            case R.id.back:
                Intent intent_back=getIntent();
                setResult(3,intent_back);
                finish();
                /*Intent intent=new Intent(CityPickerActivity.this, CityPickerActivity.class);
                startActivity(intent);*/

                break;
        }
    }

    public void getCityData() {
        Log.w("base","getCitydata_执行中1");

        String json = ReadAssetsFileUtil.getJson(this, "city.json");
        CityPickerBean bean = GsonUtil.getBean(json, CityPickerBean.class);
        HashSet<City> citys = new HashSet<>();
        for (AreasBean areasBean : bean.data.areas) {
            String name = areasBean.name.replace("　", "");

            citys.add(new City(areasBean.id, name, PinyinUtils.getPinYin(name), areasBean.is_hot == 1));

            for (AreasBean.ChildrenBeanX childrenBeanX : areasBean.children) {
                citys.add(new City(childrenBeanX.id, childrenBeanX.name, PinyinUtils.getPinYin(childrenBeanX.name), childrenBeanX.is_hot == 1));
            }
        }

        Log.w("base","getCitydata_执行中2");
        //set转换list
        ArrayList<City> cities = new ArrayList<>(citys);
        //按照字母排序
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city, City t1) {
                return city.getPinyin().compareTo(t1.getPinyin());
            }
        });

        mCityAdapter.setData(cities);
    }
    protected void initData() {
        getCityData();
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) { //选择城市

                Toast.makeText(CityPickerActivity.this, name, Toast.LENGTH_SHORT).show();

                Intent intent_ityselect=getIntent();
                intent_ityselect.putExtra("city_name",name);
                setResult(1,intent_ityselect);
                finish();
            }

            @Override
            public void onLocateClick() {//点击定位按钮
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                getLocation();//重新定位
            }
        });
    }

    /**
     * 得到位置信息(高德地图)
     */
    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //获取一次定位结果：
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //声明定位回调监听器
    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    if (amapLocation.getCity() != null && !amapLocation.getCity().equals("")) {
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, amapLocation.getCity().replace("市", ""));
                    } else {
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                } else {
                    mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("高德地图", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            //销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }


    /**
     * 隐藏软件盘
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 设置沉浸式状态栏
     */
    private void setSystemBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 LOLLIPOP解决方案
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.rgb(170,235,235));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4 KITKAT解决方案
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
