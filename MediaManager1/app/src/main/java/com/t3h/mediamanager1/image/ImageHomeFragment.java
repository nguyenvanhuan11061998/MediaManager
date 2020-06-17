package com.t3h.mediamanager1.image;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentImageBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.MediaListener;
import com.t3h.mediamanager1.interfaceFragment.ClickFmListener;
import com.t3h.mediamanager1.models.Image;

import java.io.File;
import java.util.ArrayList;

public class ImageHomeFragment extends BaseFragment<FragmentImageBinding> implements MediaListener<Image>,  ValueEventListener, View.OnClickListener, ClickFmListener {

    public static final String EXTRA_PLAY_IMAGE = "extra_play_image";
    private TextView tvCheckAll;
    private CheckBox cbAllImg;
    private LinearLayout ll_fm_img;

    private BaseAdapter<Image> adapter;

    private ArrayList<Image> arrImage;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private FileStorage fileStorage;

    private ImageHomePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null){
            presenter = ImageHomePresenter.getInstance();
        }
        initFm();

    }

    private void initFm() {
        tvCheckAll = getParent().findViewById(R.id.tv_checkedAllImg);
        cbAllImg = getParent().findViewById(R.id.cb_all_img);
        ll_fm_img = findViewById(R.id.ll_fm_image);

        tvCheckAll.setVisibility(View.INVISIBLE);
        cbAllImg.setVisibility(View.INVISIBLE);
        ll_fm_img.setVisibility(View.INVISIBLE);


        fileStorage = new FileStorage(getContext());
        adapter = new BaseAdapter<>(getContext(),R.layout.item_image);
        binding.lvPhoto.setAdapter(adapter);
        arrImage = systemData.getImages();

        adapter.setData(arrImage);

        adapter.setListener(this);
        reference = database.getReference("Image");
        reference.addValueEventListener(this);
        binding.setListener(this);


        tvCheckAll.setOnClickListener(this);
        cbAllImg.setOnClickListener(this);
    }

//=================== Xử lý click vào item ========================================================    
    
    @Override
    public void onItemMediaClick(Image image) {

        Intent intent = new Intent(getContext(), PlayModelActivity.class);
        String data = image.getData();
        intent.putExtra(EXTRA_PLAY_IMAGE,data);
        intent.putExtra(getParent().EXTRA_INDEX_FM,1);
        startActivity(intent);

    }
//long click vào item 
    @Override
    public boolean onItemMediaLongClick(Image image) {

        cbAllImg.setVisibility(View.VISIBLE);
        tvCheckAll.setVisibility(View.VISIBLE);
        ll_fm_img.setVisibility(View.VISIBLE);

        for (int i = 0; i < arrImage.size(); i++) {
            arrImage.get(i).setDisplay(View.VISIBLE);
            adapter.notifyItemChanged(i);
        }
        return true;

    }



    @Override
    public void onClickChecked(Image image) {
        int index = arrImage.indexOf(image);
        boolean checked = arrImage.get(index).getChecked();
        if (checked){
            arrImage.get(index).setChecked(false);
        }else {
            arrImage.get(index).setChecked(true);
        }
        adapter.notifyItemChanged(index);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_img:
                if (cbAllImg.isChecked()){
                    for (Image img: arrImage) {
                        img.setChecked(true);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    for (Image img: arrImage) {
                        img.setChecked(false);
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onClickHilder() {
        boolean check = false;
        for (Image img : arrImage) {
            if (img.getChecked()){
                File file = new File(img.getData());
                fileStorage.moveImgToInternal(file);
                check = true;
            }
        }
        if(check){
            Toast.makeText(getContext(),"Đã dấu ảnh", Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Chọn ảnh để dấu",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickDelete() {
        boolean check = false;
        for (Image img : arrImage) {
            if (img.getChecked()){
                File file = new File(img.getData());
                Log.e(" ======= ",file.getPath());
                check = true;
                fileStorage.deleteFile(getContext(),file);
            }
        }
        if (check == true){
            Toast.makeText(getContext()," Đã xóa ảnh", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"Chọn ảnh để xóa",Toast.LENGTH_LONG).show();
            return;
        }
        initFm();
    }


}
