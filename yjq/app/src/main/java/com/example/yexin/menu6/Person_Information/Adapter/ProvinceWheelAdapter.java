package com.example.yexin.menu6.Person_Information.Adapter;

import android.content.Context;

import com.example.yexin.menu6.Person_Information.Model.AddressDetailsEntity;

import java.util.List;

/**
 * Created by 23646 on 2019/10/22.
 */

public class ProvinceWheelAdapter extends com.example.yexin.menu6.Person_Information.Base.BaseWheelAdapter<AddressDetailsEntity.ProvinceEntity> {
    public ProvinceWheelAdapter(Context context, List<AddressDetailsEntity.ProvinceEntity> list) {
        super(context,list);
    }

    @Override
    protected CharSequence getItemText(int index) {
        AddressDetailsEntity.ProvinceEntity data = getItemData(index);
        if(data != null){
            return data.Name;
        }
        return null;
    }
}
