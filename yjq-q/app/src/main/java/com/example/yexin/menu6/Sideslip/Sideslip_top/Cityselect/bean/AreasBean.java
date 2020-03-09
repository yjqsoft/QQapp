package com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean;

/**
 * Created by ye xin on 2019/10/13.
 */

import java.util.List;

public class AreasBean {
    public int id;
    public String name;
    public int parent_id;
    public int is_hot;
    public List<ChildrenBeanX> children;

    public static class ChildrenBeanX {
        public int id;
        public String name;
        public int parent_id;
        public int is_hot;
        public List<ChildrenBean> children;

        public static class ChildrenBean {
            public int id;
            public String name;
            public int parent_id;
            public int is_hot;
        }
    }
}
