package com.example.yexin.menu6.Index;


import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;

import android.util.Log;
//import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Ballculb.Payfaceture;
import com.example.yexin.menu6.Common.Json.WebMoreInfomation;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Order.OrderFormActivity;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Share.Tickling;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.CityPickerActivity;
import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Head_photo.PhotoImageView;
import com.example.yexin.menu6.Person_Information.Activitys.PersonInformationActivity;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Setting.SettingActivity;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Share.Sideslip_share;
import com.example.yexin.menu6.Common.Thread.Thread_one;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.net.URL;
import java.util.HashMap;
import java.util.zip.Inflater;

import static com.example.yexin.menu6.R.drawable.username;
import static com.example.yexin.menu6.R.id.imageView_sideslip;
import static com.example.yexin.menu6.R.id.toolbar;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private Thread_one thread_one=null;
    private Thread thread=null;
    private ImageView UserHead=null;
    private TextView UserName;
    private TextView UserLevel=null;
    private static TextView tv_locate_city;
    private static PhotoImageView imageview_sideslip;
    private User_public user_public=null;

    // private LayoutInflater layoutInflater;
    /*底部导航栏按钮监听事件*/
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_games:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_friends:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(getResources().getColor(R.color.c4));
        }
        super.onCreate(savedInstanceState);
        user_public=new User_public(this);
        /**
         * 判断长连接，有效：继续使用。无效：去从新登入
         */
        Judge();
        setContentView(R.layout.activity_main);
//       /* View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.nav_header_main, null, false);*/
//        View view =
//        UserName = (TextView)view.findViewById(R.id.UserName);
//        UserName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"aa",Toast.LENGTH_SHORT).show();
//            }
//        });
//        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        /**
         * 获取信息
         */
        /**
         * 判断的线程，这里是判断长连接是否失效。启用线程，判断长连接用的
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView tv_search = (TextView)findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search= new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent_search);
                finish();
            }
        });
        Intent intent=getIntent();
        /*设置控件监听器*/
        View parentView;
        setSupportActionBar(toolbar);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //预设三个fragment
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        /*修改toggle原有顶部button*/
        toggle.syncState();/*显示button*/
        toggle.setDrawerIndicatorEnabled(false);/*取消原有button*/
        toggle.setHomeAsUpIndicator(R.drawable.icon_setting);/*添加自己的button*/
        toggle.setToolbarNavigationClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (User_public.isUser_flag()) {   //进入主界面，暂注释
                Log.e("ongao","杨家齐");
                drawer.openDrawer(GravityCompat.START);
                //进入主界面，暂注释
//            } else {
//                Toast.makeText(MainActivity.this, "请先登入！", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, Register.class);
//                startActivity(intent);
//                finish();
//            }
            }
        });
//        UserLevel.setText(UserPublic.getUser_level());
//        ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
//        x.image().bind(UserHead, UserPublic.getIcon(),options);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        if(navigationView.getHeaderCount() > 0) {
        View header = navigationView.getHeaderView(0);
        UserName = (TextView) header.findViewById(R.id.UserName);
        UserLevel=(TextView) header.findViewById(R.id.UserLevel);
        UserHead=(ImageView)header.findViewById(R.id.imageView_sideslip);
        UserName.setText(UserPublic.getUser_n());
        UserLevel.setText(UserPublic.getUser_level());
        ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
        x.image().bind(UserHead, UserPublic.getIcon(),options);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new oneFragment());
        adapter.addFragment(new twoFragment());
        adapter.addFragment(new threeFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_indent) {
            Intent intent_indent=new Intent(MainActivity.this, OrderFormActivity.class);
            startActivity(intent_indent);
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_wallet) {
            Toast.makeText(this,"2",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_erweima) {
            Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Payfaceture.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_share) {
           /*
           * 分享功能
           * 邱在杰  10.19 修改
           * */
            startActivity(Sideslip_share.share());
            // startActivity(Intent.createChooser(Sideslip_share.share(),"选择分享应用"));
        } else if (id == R.id.nav_yijianfankui) {
            Toast.makeText(this,"5",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Tickling.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_help) {
            Toast.makeText(this,"6",Toast.LENGTH_LONG).show();
        } else if (id ==R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onClick(View view){
        switch(view.getId()){
            case imageView_sideslip:

                /*imageview_sideslip=(PhotoImageView)findViewById(R.id.imageView_sideslip);*//*实例化头像控件*//*
                checkPermission(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setAction(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);*/
                Intent intent_one = new Intent(MainActivity.this, PersonInformationActivity.class);
                startActivity(intent_one);
                finish();
                break;
            case R.id.tv_locate_city:
                tv_locate_city=(TextView)findViewById(R.id.tv_locate_city);
                // tv_locate_city.setText("南昌shi");
                Intent intent1 = new Intent(MainActivity.this,CityPickerActivity.class);
                startActivityForResult(intent1,1);
                break;
            /*case R.id.UserName:
                Toast.makeText(MainActivity.this,"aa",Toast.LENGTH_LONG).show();
                UserName.setText("杨家齐");
                break;*/
            default:
                break;
        }
        //跳转注册界面
//        Intent intent = new Intent(MainActivity.this,Register.class);
//        startActivity(intent);
//        finish();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.w("base:requestCode","wd"+requestCode);
        super.onActivityResult( requestCode,  resultCode,  data);
        switch (requestCode) {
            case 1:
                if (resultCode == 1) {
                    if(data!=null)
                    {
                        tv_locate_city.setText(data.getStringExtra("city_name")+"市");
                    }
                }



                // Log.w("base:requestCode","wd"+data.getStringExtra("city_name"));

	        /*if (resultCode == RESULT_OK) {
	          Intent intent = new Intent("com.android.camera.action.CROP");
	          intent.setDataAndType(imageUri, "image/*");
	          intent.putExtra("scale", true);
	          intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	          // 启动裁剪程序
	          startActivityForResult(intent, CROP_PHOTO);*
	        } */
                break;

            case 3:
	        /*if (resultCode == RESULT_OK) {
	          try {
	            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
	            // 显示裁剪后的图片
	            picture.setImageBitmap(bitmap);
	          }
	          catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	          }
	        }*/
	        /*  该薇返回函数 接收*/
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    handleImage(data);

                }
                break;
            case 4:
                break;

            default:
                break;

        }
    }

    // 只在Android4.4及以上版本使用
    //@TargetApi(19)
    private void handleImage(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();

        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 通过document id来处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                // 解析出数字id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }

        } else if ("content".equals(uri.getScheme())) {
            // 如果不是document类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }

        // 根据图片路径显示图片
        // String path1="/storage/emulated/0/Tencent/QQ_Images/-76a6c6c788b562b6.jpg";
        //displayImage(path1);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        Log.w("base:uri",uri.toString());
        String path = null;
        // 通过Uri和selection来获取真实图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                Log.w("path",path);
            }
            cursor.close();
        }

        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageview_sideslip.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "失败 to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermission(Activity activity) {
        // Storage Permissions
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.CALL_PHONE,/*CALL_PHONE/*READ_EXTERNAL_STORAGE*/
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        try {
            //检测是否有拨号的权限
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                // requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 界面退出
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("yjq123","杨家齐，MainActivity已经注销");
    }
    public void Judge(){
        Log.e("yjq_six","不是第一次登入，判断长连接");
        Log.e("开启界面的判断，是否需要长连接",""+ UserPublic.isFirst());
        if(UserPublic.isFirst()){
            UserPublic.setFirst(false);
            thread_one=new Thread_one(MainActivity.this,UserName,UserLevel,UserHead);
            thread=new Thread(thread_one);
            thread.run();
        }
    }
//    public boolean Overdue(){
//        boolean flag=true;
//        String url = "http://172.22.70.227:8080/ByteSoft_two/Login";
//        RequestParams params = new RequestParams(url);
//        // params2 = new RequestParams(URL1);
//        //Log.w("WangJ", "运行在这个ok"+params.toString());
//        Log.w("WangJ", "运行在这个ok");
//        String jsonObject= Web_Json.Login_one(User_public.getUser().toString(),User_public.getUser_str().toString());
//        Log.e("Json", jsonObject);
//        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部\
//        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//        params.setBodyContent(jsonObject);//添加json内容到请求参数里
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                /**
//                 * 如果返回的报文为200，没有失效，登入成功，换取长连接
//                 * 如果为500，长连接失效，登入失败，提示去重新登入
//                 */
//                map=Web_Json.getJson(result);
//                Log.e("yjq_four","新的长连接：code"+map.get("code")+"message"+map.get("message")+"token"+map.get("token"));
//            }
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(Login.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                Log.e("yjq1","失败");
//            }
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Log.e("yjq","取消");
//            }
//            @Override
//            public void onFinished() {
//                Log.e("yjq","完成");
//            }
//        });
//        if(map.get("code").equals("200")){
//            Log.e("yjq_11","长连接没有失效，并且更新了长连接");
//        }
//        else{
//            Log.e("yjq_12","长连接失效了重新去登入");
//            flag=false;
//        }
//        return flag;
//    }
//    public void Layout_init(){
//        tv_register = (TextView)findViewById(R.id.tv_register);
//        text_username=(EditText)findViewById(R.id.et_username);
//        text_password=(EditText)findViewById(R.id.et_password);
//        button_submit=(Button)findViewById(R.id.btn_login);
//        button_submit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (!TextUtils.isEmpty(text_username.getText().toString())
//                        && !TextUtils.isEmpty(text_password.getText().toString())) {
//                    Log.e("WangJ", "都不空");
//                    /**
//                     * 点击登入，账号密码都不为空的时候，发起网络请求
//                     */
//                    String url = "http://172.22.70.227:8080/ByteSoft_two/Login";
//                    RequestParams params = new RequestParams(url);
//                    // params2 = new RequestParams(URL1);
//                    //Log.w("WangJ", "运行在这个ok"+params.toString());
//                    Log.w("WangJ", "运行在这个ok");
//                    String jsonObject= Web_Json.Login(text_username.getText().toString(),text_password.getText().toString());
//                    Log.e("Json", jsonObject);
//                    params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部\
//                    params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//                    params.setBodyContent(jsonObject);//添加json内容到请求参数里
//                    x.http().post(params, new Callback.CommonCallback<String>() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Log.e("yjq",result);//接收JSON的字符串
//                            HashMap<String,String> map=Web_Json.getJson(result);
//                            SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
//                            SharedPreferences.Editor editor=preferences.edit();
//                            editor.putBoolean("User_flag",true);
//                            editor.putString("User",text_username.toString());
//                            editor.putString("Icon",null);
//                            editor.putString("User_str",map.get("token"));
//                            editor.putString("Icon_time",null);
//                            editor.commit();
//                            User_public.setUser(preferences.getString("User",text_username.toString()));
//                            User_public.setUser_flag(preferences.getBoolean("User_flag",true));
//                            User_public.setUser_str(preferences.getString("User_str",map.get("token")));
//
//                            Log.e("yjq1", "编码:"+map.get("code")+map.get("message"));
//                        }
//                        @Override
//                        public void onError(Throwable ex, boolean isOnCallback) {
//                            Toast.makeText(MainActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                            Log.e("yjq1","失败");
//                        }
//                        @Override
//                        public void onCancelled(CancelledException cex) {
//                            Log.e("yjq","取消");
//                        }
//                        @Override
//                        public void onFinished() {
//                            Log.e("yjq","完成");
//                        }
//                    });
//                } else {
//                    Log.e("WangJ", "都空");
//                    Toast.makeText(MainActivity.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

}
