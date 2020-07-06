package com.t3h.mediamanager1.media.video.adapter;

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
import com.t3h.mediamanager1.media.video.model.VideoModel;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<VideoModel> arrVideo = new ArrayList<>();
    private LayoutInflater inflater;

    public VideoAdapter(Context context, ArrayList<VideoModel> arrVideo) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.arrVideo = arrVideo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View videoView = inflater.inflate(R.layout.item_video_new,parent,false);
        return new ViewHolder(videoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoModel videoModel = arrVideo.get(position);
        holder.binData(videoModel);
    }

    @Override
    public int getItemCount() {
        return arrVideo == null ? 0 : arrVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.video_view)
        SquareImageView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void binData(VideoModel videoModel) {
            Glide.with(context).load(new File(videoModel.getUri().toString()))
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(18)))
                    .into(videoView);
        }
    }
}
