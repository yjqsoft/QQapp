package com.example.yexin.menu6.Index.BaiduMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.yexin.menu6.R;

public class Stadium_location_show extends Activity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private InfoWindow mInfoWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stadium_location_show);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        Intent intent =getIntent();
        String  addressString=intent.getStringExtra("address");
        String location=intent.getStringExtra("location");
        //用来构造InfoWindow的Button
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.popup);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        button.setText(addressString);  //显示位置的信息
        String []xy=location.split(",");
        double a=new Double(xy[0]);
        double b=new Double(xy[1]);
//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
        Log.e("zbzb",a+"DDD"+b);
        LatLng llText = new LatLng(a,b);
        //自定义位置（坐标）
        mInfoWindow = new InfoWindow(button, llText, -150);//信息描述的位置

        OverlayOptions option1 =  new MarkerOptions()
                .position(llText)
                .icon(bitmap);
        // options.add(option1);
        // mBaiduMap.addOverlays(option1);//添加多个点  用列表List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        mBaiduMap.addOverlay(option1);

        //mInfoWindow1 = new InfoWindow(button, llText, -100);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                //要移动的点
                .target(llText)//需要放大的位置
                //放大地图到20倍
                .zoom(30)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

        //改变地图状态（使设置的地图状态生效）
        //  DistanceUtil. getDistance(llText, llText);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        //使InfoWindow生效
        mBaiduMap.showInfoWindow(mInfoWindow);


    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}
