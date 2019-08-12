package com.t3h.mediamanager1.base;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.t3h.mediamanager1.BR;
import com.t3h.mediamanager1.models.Model;

import java.util.ArrayList;

public class BaseAdapter <T extends Model> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private ListItemListener listener;
    private ArrayList<T> data;
    private LayoutInflater inflater;
    private @LayoutRes int resId;

//==================================================================================================
    public BaseAdapter(Context context, @LayoutRes int resId){
        inflater = LayoutInflater.from(context);
        this.resId = resId;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(ListItemListener listener) {
        this.listener = listener;
    }

    public ArrayList<T> getData() {
        return data;
    }

    //==================================================================================================

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,resId,viewGroup,false);
        return new ViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder viewHolder, int i) {
        T t = data.get(i);
        viewHolder.binding.setVariable(BR.item,t);
        viewHolder.binding.setVariable(BR.listener,listener);
        viewHolder.binding.executePendingBindings();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public interface ListItemListener{
    }
}
