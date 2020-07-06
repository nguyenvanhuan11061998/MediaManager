package com.t3h.mediamanager1.media.music.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.SquareImageView;
import com.t3h.mediamanager1.media.music.model.MusicModel;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MusicModel> arrMusic = new ArrayList<>();
    private onClickItemMusic clickItemMusic;

    public MusicAdapter(Context context, ArrayList<MusicModel> arrMusic) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.arrMusic = arrMusic;
    }

    public void setArrMusic(ArrayList<MusicModel> arrMusic) {
        this.arrMusic = arrMusic;
        notifyDataSetChanged();
    }

    public void setClickItemMusic(onClickItemMusic clickItemMusic) {
        this.clickItemMusic = clickItemMusic;
    }

    public ArrayList<MusicModel> getArrMusic() {
        return arrMusic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View musicView = inflater.inflate(R.layout.item_music_new,parent,false);
        return new ViewHolder(musicView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MusicModel musicModel = arrMusic.get(position);
        holder.binData(musicModel);
    }

    @Override
    public int getItemCount() {
        return arrMusic == null ? 0 : arrMusic.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.image_music)
        SquareImageView musicImage;
        @BindView(R.id.tv_title)
        TextView titleTextView;
        @BindView(R.id.tv_artist)
        TextView artistTextView;
        @BindView(R.id.tv_date)
        TextView dateTextView;
        @BindView(R.id.ll_item_music)
        LinearLayout itemMusicView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void binData(MusicModel musicModel) {
            Glide.with(context).load(new File(musicModel.getUri().toString()))
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(18)))
                    .error(R.drawable.ic_music_not_data)
                    .into(musicImage);
            titleTextView.setText(musicModel.getName());
            artistTextView.setText(musicModel.getArtist());
            dateTextView.setText(musicModel.getDate()+"");

            itemMusicView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemMusic!=null){
                        clickItemMusic.onClickItemMusic(musicModel);
                    }
                }
            });
        }
    }

    public interface onClickItemMusic{
        void onClickItemMusic(MusicModel musicModel);
    }
}
