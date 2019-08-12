package com.t3h.mediamanager1.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.mediamanager1.App;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.dao.SystemData;

public abstract class BaseFragment <BD extends ViewDataBinding> extends Fragment {

    protected BD binding;
    protected SystemData systemData;

    protected App app;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        systemData = new SystemData(getContext());

        app = (App) getContext().getApplicationContext();
    }

    protected abstract int getLayoutId();

    protected <T extends View> T findViewById(@IdRes int id){
        return getActivity().findViewById(id);
    }

    protected MainActivity getParent(){
        return (MainActivity) getActivity();
    }

    protected PlayModelActivity getPlayMolelParent(){
        return (PlayModelActivity) getActivity();
    }

}
