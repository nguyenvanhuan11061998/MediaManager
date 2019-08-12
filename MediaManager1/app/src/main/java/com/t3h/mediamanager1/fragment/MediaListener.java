package com.t3h.mediamanager1.fragment;

import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.models.Model;

public interface MediaListener <T extends Model> extends BaseAdapter.ListItemListener {

    void onItemMediaClick(T t);
    boolean onItemMediaLongClick(T t);
    void onClickChecked(T t);

}
