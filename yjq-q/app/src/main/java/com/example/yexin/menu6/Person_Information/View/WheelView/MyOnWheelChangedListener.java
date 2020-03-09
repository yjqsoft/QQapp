package com.example.yexin.menu6.Person_Information.View.WheelView;

/**
 * Created by 23646 on 2019/10/22.
 */

public interface 	MyOnWheelChangedListener {
    /**
     * Callback method to be invoked when current item changed
     * @param wheel the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */
    void onChanged(MyWheelView wheel, int oldValue, int newValue);
}
