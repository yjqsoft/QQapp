package com.example.yexin.menu6.Person_Information.Activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Modify;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.Login;
import com.example.yexin.menu6.Person_Information.Model.AddressDetailsEntity;
import com.example.yexin.menu6.Person_Information.Model.AddressModel;
import com.example.yexin.menu6.Person_Information.Utils.JsonUtil;
import com.example.yexin.menu6.Person_Information.Utils.Utils;
import com.example.yexin.menu6.Person_Information.View.ChooseAddressWheel;
import com.example.yexin.menu6.Person_Information.View.Listener.OnAddressChangeListener;
import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Head_photo.PhotoImageView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;

public class PersonInformationActivity extends Activity implements OnAddressChangeListener {
    private ImageView iv_head;
    private TextView tv_username;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_level;
    private TextView tv_phone;
    private TextView tv_birth;
    private TextView tv_credit_card;
    private TextView tv_location;
    private TextView tv_signature;
    private String place=null;
    private String text=null;
    private boolean isSuccess=false;
    private boolean isRealInfo=false;
    private int year,month,day;
    private static PhotoImageView imageview_sideslip;

    private ChooseAddressWheel chooseAddressWheel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_information);
        GetReallyInfo();
        Initial();


    }

    //控件初始化
    public void Initial(){
        iv_head = (ImageView)findViewById(R.id.iv_head);
        tv_username = (TextView)findViewById(R.id.tv_username);
        tv_name = (TextView)findViewById(R.id.tv_nickname);
        tv_sex = (TextView)findViewById(R.id.tv_sex);
        tv_level = (TextView)findViewById(R.id.tv_level);
        tv_phone = (TextView)findViewById(R.id.tv_phone);
        tv_birth = (TextView)findViewById(R.id.tv_birth);
        tv_credit_card = (TextView)findViewById(R.id.tv_credit_card);
        tv_location = (TextView)findViewById(R.id.tv_location);
        tv_signature = (TextView)findViewById(R.id.tv_signature);
        /**
         * 初始化控件的值
         */
        InitText();
        init();
    }
    private void InitText(){
//        tv_username.setText(UserPublic.getUser());  //用户真实姓名   1
        ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
        x.image().bind(iv_head, UserPublic.getIcon(),options);
        tv_name.setText(UserPublic.getUser_n());  //用户昵称   2
        tv_sex.setText(UserPublic.getUser_sex());  //用户性别   3
        tv_level.setText(UserPublic.getUser_level());  //用户等级   4
        tv_phone.setText(UserPublic.getUser());  //用户电话号码   5
        tv_birth.setText(UserPublic.getUser_datetime());  //用户生日   6
//        tv_credit_card.setText("");  //用户银行卡   7
        tv_location.setText(UserPublic.getUser_place());  //用户地址   8
        tv_signature.setText(UserPublic.getUser_q());  //用户签名   9
    }
    //设置控件单击事件
    public void onClick(View view){
        switch (view.getId()){
            //单击界面返回键返回上一级界面
            case R.id.iv_back:
                Intent intent1 = new Intent(PersonInformationActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;

            //单击更换头像
            case R.id.rl_head:

                break;

            //单击跳转真实姓名编辑界面
//            case R.id.rl_username:
//                Intent intent_one = new Intent(PersonInformationActivity.this,UserNameActivity.class);
//                startActivityForResult(intent_one,1);
//                break;
            //单击跳转昵称编辑界面
            case R.id.rl_nickname:
                Intent intent_two = new Intent(PersonInformationActivity.this,NickNameActivity.class);
                startActivityForResult(intent_two,2);
                break;
            //单击弹出性别选择对话框
            case R.id.rl_sex:
                new AlertDialog.Builder(this)
                        .setTitle("请选择性别")
                        .setSingleChoiceItems(new String[]{"男", "女"}, 0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                        tv_sex.setText(UserPublic.getUser_sex());  //用户性别   3
                                    }
                                })
                        .setPositiveButton("完成",null)
                        .show();
                break;

            //单击跳转手机号码编辑界面
//            case R.id.rl_phone:
//                Intent intent_three= new Intent(PersonInformationActivity.this,TelephoneActivity.class);
//                startActivityForResult(intent_three,3);
//                break;

            //单击弹出日期选择对话框
            case R.id.rl_birth:
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(calendar.YEAR);
                month = calendar.get(calendar.MONTH);
                day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        text = year + "-"+(month+1)+"-"+day;
                        Xutils(text,"7");
                    }
                },year,month,day);
                dateDialog.show();
                break;

            //单击跳转地区编辑界面
            case R.id.rl_location:
                /*Intent intent_four= new Intent(MainActivity.this,LocationActivity.class);
                startActivity(intent_four);
                finish();*/
                Utils.hideKeyBoard(this);

                chooseAddressWheel.show(view);
                Log.e("base","Utils");
                break;

            //单击跳转个性签名编辑界面
            case R.id.rl_signature:
                Intent intent_five= new Intent(PersonInformationActivity.this,SignatureActivity.class);
                startActivityForResult(intent_five,4);
                break;
            case R.id.iv_head:
                Intent intent = new Intent(PersonInformationActivity.this,Header.class);
                startActivity(intent);
//                checkPermission(PersonInformationActivity.this);
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setAction(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 2);
                break;
        }
    }

    @Override
    public void onAddressChange(String province, String city, String district) {
        place=province+" "+city+" "+district;
        Xutils(place,"9");
        //tv_location.setText(province + " " + city + " " + district);
    }

    private void init() {

        initWheel();

        initData();

    }

    private void initWheel() {

        chooseAddressWheel = new ChooseAddressWheel(PersonInformationActivity.this);

        chooseAddressWheel.setOnAddressChangeListener(this);


    }

    private void initData() {
        String address = Utils.readAssert(this, "address.txt");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressDetailsEntity data = model.Result;
            if (data == null) return;
            //tv_location.setText(data.Province + " " + data.City + " " + data.Area);
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
    }

    //点击手机返回键返回上一级界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(PersonInformationActivity.this,MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        super.onActivityResult(request,result,data);
        if(data!=null){
            if(request==1&&result==1){  //真实姓名的改
                Log.e("aoga","修改姓名");
                tv_username.setText(data.getSerializableExtra("name").toString());

            }
            else if(request==2&&result==2){//昵称的改
                Log.e("aoga","修改昵称");
                tv_name.setText(data.getSerializableExtra("nickname").toString());

            }
            else if(request==3&&result==3){ //手机号码改
                Log.e("aoga","修改手机号码");
                tv_phone.setText(data.getSerializableExtra("phone").toString());
            }
            else if(request==4&&result==4){ //个性签名改
                Log.e("aoga","修改个性签名");
                tv_signature.setText(data.getSerializableExtra("signature").toString());
            }
            else{
                Log.e("1","没有修改");
            }
        }
    }
    private void Xutils(String content, final String model) {

        String jsonObject = Modify.setInfo(content, model);
        RequestParams params = new RequestParams(Web_url.URL_Modifyinfo);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(jsonObject);//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq", "网络请求成功" + result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                HashMap<String, String> map = Modify.getModify(result);

                Log.e("124", map.get("code").toString());
                if (map.get("code").toString().equals("200")) {
                    isSuccess = true;
                } else {
                    isSuccess = false;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1", "失败");
                isSuccess = false;
                Toast.makeText(PersonInformationActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq", "取消");
            }

            @Override
            public void onFinished() {
                Log.e("yjq", "完成");
                if (isSuccess) {
//                    Log.e("ahgia", "修改成功");
//                    Intent intent = new Intent();
//                    intent.putExtra("nickname", text);
//                    setResult(2, intent);
//                    finish();
                    if(model.equals("9")){
                        tv_location.setText(place);
                        UserPublic.setUser_place(place);
                    }
                    else{
                        tv_birth.setText(text);  //用户生日   6
                        UserPublic.setUser_datetime(text);
                    }
                } else {
//                    Intent intent = new Intent();
//                    intent.putExtra("nickname", "");
//                    setResult(10, intent);
//                    finish();
                    if(model.equals("9")){
                        tv_location.setText(UserPublic.getUser_place());  //用户地址   8
                    }
                    else{
                        tv_birth.setText(UserPublic.getUser_datetime());  //用户生日   6
                    }
                    Toast.makeText(PersonInformationActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    Log.e("gaodifha", "修改失败");
                }
            }
        });
    }
    private void GetReallyInfo(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Username", UserPublic.getUser());
            Log.d("sa", jsonObject.toString());
            RequestParams params = new RequestParams(Web_url.URL_ReallyInfo);
            params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
            params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
            params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里
            x.http().post(params, new Callback.CommonCallback<String>() {
                private String code=null;
                private String ReallyName=null;
                private String Readllycard=null;
                @Override
                public void onSuccess(String result) {
                    Log.e("yjq", "网络请求成功" + result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                    JSONObject jsonObject=null;
                    try{
                        jsonObject=new JSONObject(result);
                        code=jsonObject.get("code").toString();
                        ReallyName=jsonObject.get("ReallyName").toString();
                        Readllycard=jsonObject.get("Reallycard").toString();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (code.equals("200")) {
                        isRealInfo = true;
                    } else {
                        isRealInfo = false;
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("yjq1", "失败");
                    isRealInfo = false;
                    Toast.makeText(PersonInformationActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Log.e("yjq", "取消");
                }

                @Override
                public void onFinished() {
                    Log.e("yjq", "完成");
                    if (isRealInfo) {
//                    Log.e("ahgia", "修改成功");
//                    Intent intent = new Intent();
//                    intent.putExtra("nickname", text);
//                    setResult(2, intent);
//                    finish();
                        tv_username.setText(ReallyName);  //用户真实姓名
                        tv_credit_card.setText(Readllycard);  //用户银行卡   7

                    } else {
//                    Intent intent = new Intent();
//                    intent.putExtra("nickname", "");
//                    setResult(10, intent);
//                    finish();
                        tv_username.setText(UserPublic.getUser());
                        tv_credit_card.setText("");  //用户银行卡   7
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("yjq123","杨家齐，个人信息");
    }
}
