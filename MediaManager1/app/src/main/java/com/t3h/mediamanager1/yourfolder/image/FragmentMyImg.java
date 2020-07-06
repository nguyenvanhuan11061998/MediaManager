package com.t3h.mediamanager1.yourfolder.image;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.DriveActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentYourImgBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.MediaListener;
import com.t3h.mediamanager1.interfaceFragment.MyFmListener;
import com.t3h.mediamanager1.models.Image;

import java.io.File;
import java.util.ArrayList;

public class FragmentMyImg extends BaseFragment<FragmentYourImgBinding> implements MediaListener<Image>, View.OnClickListener, MyFmListener {

    private FileStorage fileStorage;
    private TextView tvCheckedAll;
    private CheckBox cbAllImg;

    private BaseAdapter<Image> adapter;
    private ArrayList<Image> arrImage = new ArrayList<>();
    private LinearLayout llImgFolder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_your_img;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();
    }

    @Nullable

    private void initFm() {
        tvCheckedAll = findViewById(R.id.tv_checkedAll_Img_Fd);
        cbAllImg = findViewById(R.id.cb_all_img_of_fd);
        llImgFolder = findViewById(R.id.LL_My_Img);

        tvCheckedAll.setVisibility(View.INVISIBLE);
        cbAllImg.setVisibility(View.INVISIBLE);
        llImgFolder.setVisibility(View.INVISIBLE);


        fileStorage = new FileStorage(getContext());

        arrImage = fileStorage.getImage();

        adapter = new BaseAdapter<>(getContext(),R.layout.item_image);
        binding.lvPhoto1.setAdapter(adapter);
        adapter.setData(arrImage);

        binding.setListener(this);
        adapter.setListener(this);
        cbAllImg.setOnClickListener(this);

    }

    @Override
    public void onItemMediaClick(Image image) {
        getPlayMolelParent().setData(image.getData());
        getPlayMolelParent().showFmPlayImg();
    }

    @Override
    public boolean onItemMediaLongClick(Image image) {

        for (Image img:arrImage) {
            img.setDisplay(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();

        tvCheckedAll.setVisibility(View.VISIBLE);
        cbAllImg.setVisibility(View.VISIBLE);
        llImgFolder.setVisibility(View.VISIBLE);

        return false;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_img_of_fd:
                if (cbAllImg.isChecked()){
                    for (Image img: arrImage) {
                        img.setChecked(true);
                    }
                }else {
                    for (Image img: arrImage) {
                        img.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getPlayMolelParent().takePhoto(intent);
        initFm();
    }

    @Override
    public void onClickDelete() {
        for (Image image: arrImage){
            if (image.getChecked()){
                File file = new File(image.getData());
                file.delete();
            }
        }
        initFm();
    }

    @Override
    public void onClickRestore() {
        boolean check = false;

        for (Image img: arrImage) {
            if (img.getChecked()){
                File file = new File(img.getData());
                fileStorage.moveImgtoExternal(file);
                check = true;
            }
        }

        if (check){
            Toast.makeText(getContext(),"Đã restore ảnh",Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Vui lòng chọn ảnh để restore",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickBackup() {
    }


}
