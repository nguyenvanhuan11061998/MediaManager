package com.t3h.mediamanager1.media.image.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.SquareImageView;
import com.t3h.mediamanager1.media.image.model.ImageHomeModel;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ImageHomeModel> arrImage;
    private clickImageListener listener;

    public ImageAdapter(Context context, ArrayList<ImageHomeModel> arrImage) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.arrImage = arrImage;
    }

    public void setArrImage(ArrayList<ImageHomeModel> arrImage) {
        this.arrImage = arrImage;
        notifyDataSetChanged();
    }

    public ArrayList<ImageHomeModel> getArrImage() {
        return arrImage;
    }

    public void setListener(clickImageListener clickLister){
        this.listener = clickLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = inflater.inflate(R.layout.item_image_new,parent,false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageHomeModel imageItem = arrImage.get(position);
        holder.binData(imageItem, position);
    }

    @Override
    public int getItemCount() {
        return arrImage == null ? 0 : arrImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_view)
        SquareImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void binData(ImageHomeModel imageItem, int position) {
            Glide.with(context).load(new File(imageItem.getUri().toString()))
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(18)))
                    .into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onClick(position);
                    }
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null){
                        listener.onLongClick(position);
                    }
                    return false;
                }
            });
        }
    }

    public interface clickImageListener{
        void onClick(int position);
        void onLongClick(int position);
    }
}
