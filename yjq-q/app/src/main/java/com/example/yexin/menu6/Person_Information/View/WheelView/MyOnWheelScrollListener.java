package com.example.yexin.menu6.Person_Information.View.WheelView;

/**
 * Created by 23646 on 2019/10/22.
 */

public interface MyOnWheelScrollListener {
    /**
     * Callback method to be invoked when scrolling started.
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingStarted(MyWheelView wheel);

    /**
     * Callback method to be invoked when scrolling ended.
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingFinished(MyWheelView wheel);
}
