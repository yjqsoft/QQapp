package com.example.yexin.menu6.Person_Information.View;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yexin.menu6.Person_Information.Adapter.AreaWheelAdapter;
import com.example.yexin.menu6.Person_Information.Adapter.CityWheelAdapter;
import com.example.yexin.menu6.Person_Information.Adapter.ProvinceWheelAdapter;
import com.example.yexin.menu6.Person_Information.Model.AddressDetailsEntity;
import com.example.yexin.menu6.Person_Information.Utils.Utils;
import com.example.yexin.menu6.Person_Information.View.Listener.OnAddressChangeListener;
import com.example.yexin.menu6.Person_Information.View.WheelView.MyOnWheelChangedListener;
import com.example.yexin.menu6.Person_Information.View.WheelView.MyWheelView;
import com.example.yexin.menu6.R;

import java.util.List;

/**
 * Created by 23646 on 2019/10/22.
 */

public class ChooseAddressWheel implements MyOnWheelChangedListener {


    MyWheelView provinceWheel;
    MyWheelView cityWheel;
    MyWheelView districtWheel;

    private TextView confirm_button;
    private TextView cancel_button;

    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;

    private List<AddressDetailsEntity.ProvinceEntity> province = null;

    private OnAddressChangeListener onAddressChangeListener = null;

    public ChooseAddressWheel(Activity context) {
        this.context = context;

        init();
    }

    private void init() {
        layoutParams = context.getWindow().getAttributes();
        layoutInflater = context.getLayoutInflater();
        initView();

        initPopupWindow();
    }

    private void initView() {
        parentView = layoutInflater.inflate(R.layout.choose_city_layout, null);
        //ButterKnife.bind(this, parentView);

        confirm_button = (TextView)parentView.findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onAddressChangeListener != null) {
                    int provinceIndex = provinceWheel.getCurrentItem();
                    int cityIndex = cityWheel.getCurrentItem();
                    int areaIndex = districtWheel.getCurrentItem();

                    String provinceName = null, cityName = null, areaName = null;

                    List<AddressDetailsEntity.ProvinceEntity.CityEntity> citys = null;
                    if (province != null && province.size() > provinceIndex) {
                        AddressDetailsEntity.ProvinceEntity provinceEntity = province.get(provinceIndex);
                        citys = provinceEntity.City;
                        provinceName = provinceEntity.Name;
                    }
                    List<AddressDetailsEntity.ProvinceEntity.AreaEntity> districts = null;
                    if (citys != null && citys.size() > cityIndex) {
                        AddressDetailsEntity.ProvinceEntity.CityEntity cityEntity = citys.get(cityIndex);
                        districts = cityEntity.Area;
                        cityName = cityEntity.Name;
                    }

                    if (districts != null && districts.size() > areaIndex) {
                        AddressDetailsEntity.ProvinceEntity.AreaEntity areaEntity = districts.get(areaIndex);
                        areaName = areaEntity.Name;
                    }

                    onAddressChangeListener.onAddressChange(provinceName, cityName, areaName);
                }
                popupWindow.dismiss();
            }
        });

        cancel_button = (TextView)parentView.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        provinceWheel = (MyWheelView)parentView.findViewById(R.id.province_wheel);
        cityWheel = (MyWheelView)parentView.findViewById(R.id.city_wheel);
        districtWheel = (MyWheelView)parentView.findViewById(R.id.district_wheel);


        provinceWheel.setVisibleItems(7);
        cityWheel.setVisibleItems(7);
        districtWheel.setVisibleItems(7);

        provinceWheel.addChangingListener(this);
        cityWheel.addChangingListener(this);
        districtWheel.addChangingListener(this);
    }



    private void initPopupWindow() {
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT, (int) (Utils.getScreenHeight(context) * (2.0 / 5)));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.anim_push_bottom);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                layoutParams.alpha = 1.0f;
                context.getWindow().setAttributes(layoutParams);
                popupWindow.dismiss();
            }
        });
    }

    private void bindData() {
        provinceWheel.setViewAdapter(new ProvinceWheelAdapter(context, province));
        updateCitiy();
        updateDistrict();
    }

    @Override
    public void onChanged(MyWheelView wheel, int oldValue, int newValue) {
        if (wheel == provinceWheel) {
            updateCitiy();//省份改变后城市和地区联动
        } else if (wheel == cityWheel) {
            updateDistrict();//城市改变后地区联动
        } else if (wheel == districtWheel) {
        }
    }

    private void updateCitiy() {
        int index = provinceWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.CityEntity> citys = province.get(index).City;
        if (citys != null && citys.size() > 0) {
            cityWheel.setViewAdapter(new CityWheelAdapter(context, citys));
            cityWheel.setCurrentItem(0);
            updateDistrict();
        }
    }

    private void updateDistrict() {
        int provinceIndex = provinceWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.CityEntity> citys = province.get(provinceIndex).City;
        int cityIndex = cityWheel.getCurrentItem();
        List<AddressDetailsEntity.ProvinceEntity.AreaEntity> districts = citys.get(cityIndex).Area;
        if (districts != null && districts.size() > 0) {
            districtWheel.setViewAdapter(new AreaWheelAdapter(context, districts));
            districtWheel.setCurrentItem(0);
        }

    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void setProvince(List<AddressDetailsEntity.ProvinceEntity> province) {
        this.province = province;
        bindData();
    }

    public void defaultValue(String provinceStr, String city, String arae) {
        if (TextUtils.isEmpty(provinceStr)) return;
        for (int i = 0; i < province.size(); i++) {
            AddressDetailsEntity.ProvinceEntity provinces = province.get(i);
            if (provinces != null && provinces.Name.equalsIgnoreCase(provinceStr)) {
                provinceWheel.setCurrentItem(i);
                if (TextUtils.isEmpty(city)) return;
                List<AddressDetailsEntity.ProvinceEntity.CityEntity> citys = provinces.City;
                for (int j = 0; j < citys.size(); j++) {
                    AddressDetailsEntity.ProvinceEntity.CityEntity cityEntity = citys.get(j);
                    if (cityEntity != null && cityEntity.Name.equalsIgnoreCase(city)) {
                        cityWheel.setViewAdapter(new CityWheelAdapter(context, citys));
                        cityWheel.setCurrentItem(j);
                        if (TextUtils.isEmpty(arae)) return;
                        List<AddressDetailsEntity.ProvinceEntity.AreaEntity> areas = cityEntity.Area;
                        for (int k = 0; k < areas.size(); k++) {
                            AddressDetailsEntity.ProvinceEntity.AreaEntity areaEntity = areas.get(k);
                            if (areaEntity != null && areaEntity.Name.equalsIgnoreCase(arae)) {
                                districtWheel.setViewAdapter(new AreaWheelAdapter(context, areas));
                                districtWheel.setCurrentItem(k);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setOnAddressChangeListener(OnAddressChangeListener onAddressChangeListener) {
        this.onAddressChangeListener = onAddressChangeListener;
    }
}
